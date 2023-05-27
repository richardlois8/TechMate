package id.co.ukdw.techmate.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.co.ukdw.techmate.data.database.GadgetCase
import id.co.ukdw.techmate.databinding.ItemGadgetBinding
import java.util.Locale

class GadgetAdapter(
    private var lstGadget: List<GadgetCase>?,
    private val listener: OnGadgetClickListener
) : RecyclerView.Adapter<GadgetAdapter.GadgetViewHolder>() {
    class GadgetViewHolder(val binding: ItemGadgetBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: GadgetCase, listener: OnGadgetClickListener) {
            val brand = item.brand
            val splitGoal = item.goal.split(" ")
            val model =
                if (brand.lowercase() == splitGoal[0].lowercase()) splitGoal.slice(1 until splitGoal.size)
                    .joinToString(" ") else item.goal
            binding.txtBrand.text = brand.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }
            binding.txtModel.text = model.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }

            if (item.similarity == 0.0){
                binding.txtSimilarity.visibility = ViewGroup.GONE
            } else {
                binding.txtSimilarity.visibility = ViewGroup.VISIBLE
                binding.txtSimilarity.text = String.format("Similaritty : %.2f", item.similarity) + "%"
            }

            Glide.with(binding.root.context)
                .load(item.image)
                .into(binding.imgGadget)

            binding.root.setOnClickListener {
                listener.onGadgetClicked(item)
            }
        }
    }

    interface OnGadgetClickListener {
        fun onGadgetClicked(gadget: GadgetCase)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GadgetViewHolder {
        val binding = ItemGadgetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GadgetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GadgetViewHolder, position: Int) {
        lstGadget?.let {
            holder.bind(it[position], listener)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(filteredGadgets: List<GadgetCase>) {
        this.lstGadget = filteredGadgets
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = lstGadget?.size ?: 0
}