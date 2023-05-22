package id.co.ukdw.techmate.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.co.ukdw.techmate.data.database.GadgetCase
import id.co.ukdw.techmate.databinding.ItemGadgetBinding
import java.util.*

class GadgetAdapter(val lstGadget : List<GadgetCase>) : RecyclerView.Adapter<GadgetAdapter.GadgetViewHolder>() {
    class GadgetViewHolder(val binding : ItemGadgetBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : GadgetCase) {
            val brand = item.brand
            val splitGoal = item.goal.split(" ")
            val model = if(brand.lowercase() == splitGoal[0].lowercase()) splitGoal.slice(1 until splitGoal.size).joinToString(" ") else item.goal
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

            Glide.with(binding.root.context)
                .load(item.image)
                .into(binding.imgGadget)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GadgetViewHolder {
        val binding = ItemGadgetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GadgetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GadgetViewHolder, position: Int) {
        holder.bind(lstGadget[position])
    }

    override fun getItemCount(): Int = lstGadget.size

}