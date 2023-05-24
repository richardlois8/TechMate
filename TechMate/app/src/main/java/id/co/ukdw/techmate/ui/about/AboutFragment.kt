package id.co.ukdw.techmate.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import id.co.ukdw.techmate.R
import id.co.ukdw.techmate.databinding.FragmentAboutBinding


class AboutFragment : Fragment() {
    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gadgets = listOf(
            UserData("Edwin Mahendra", R.drawable.user1_image),
            UserData("JB Adiatmaja", R.drawable.user2_image),
            UserData("Richard Lois S.", R.drawable.user3_image),
        )
        // Initialize RecyclerView
        val adapter = UserAdapter(gadgets)
        binding.recViewUser.layoutManager = GridLayoutManager(context, 1)
        binding.recViewUser.adapter = adapter
    }
}