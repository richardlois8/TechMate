package id.co.ukdw.techmate.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.co.ukdw.techmate.data.database.GadgetCase
import id.co.ukdw.techmate.databinding.ItemGadgetBinding

class GadgetAdapter(val lstGadget : List<GadgetCase>) : RecyclerView.Adapter<GadgetAdapter.GadgetViewHolder>() {
    class GadgetViewHolder(val binding : ItemGadgetBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : GadgetCase) {
            binding.txtBrand.text = item.brand
            val splitGoal = item.goal.split(" ")
            val model = splitGoal.slice(1..splitGoal.size-1).joinToString(" ")
            binding.txtModel.text = model

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