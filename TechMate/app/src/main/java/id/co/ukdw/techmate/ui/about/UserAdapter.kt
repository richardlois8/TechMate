package id.co.ukdw.techmate.ui.about

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.co.ukdw.techmate.databinding.ItemUserBinding

class UserAdapter(private val gadgets: List<UserData>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(gadget: UserData) {
            binding.txtBrand.text = gadget.name
            Glide.with(binding.root)
                .load(gadget.picture)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.imgGadget)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val gadget = gadgets[position]
        holder.bind(gadget)
    }

    override fun getItemCount(): Int = gadgets.size
}