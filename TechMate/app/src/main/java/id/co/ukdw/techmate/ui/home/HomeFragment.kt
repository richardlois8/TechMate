package id.co.ukdw.techmate.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import id.co.ukdw.techmate.MainActivity
import id.co.ukdw.techmate.R
import id.co.ukdw.techmate.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding .inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        showBottomNav()
    }

    private fun setupRecyclerView() {
        val dataGadget = (activity as MainActivity).getEngine().getAllGadget()
        binding.apply {
            recViewGadget.layoutManager = GridLayoutManager(context, 1)
            recViewGadget.adapter = GadgetAdapter(dataGadget)
        }
    }

    private fun showBottomNav() {
        val bottomNav = (activity as MainActivity).binding.bottomNav
        bottomNav.visibility = View.VISIBLE
    }
}