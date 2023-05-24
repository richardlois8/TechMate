package id.co.ukdw.techmate.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import id.co.ukdw.techmate.MainActivity
import id.co.ukdw.techmate.R
import id.co.ukdw.techmate.data.database.GadgetCase
import id.co.ukdw.techmate.databinding.FragmentHomeBinding
import java.util.Locale


class HomeFragment : Fragment(), GadgetAdapter.OnGadgetClickListener {
    private lateinit var binding : FragmentHomeBinding
    private var gadgetAdapter: GadgetAdapter? = null
    private var allGadgets: List<GadgetCase>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding .inflate(inflater, container, false)
        binding.fabHelp.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_helpFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        allGadgets = (activity as MainActivity).getEngine().getAllGadget()
        setupRecyclerView(allGadgets)
        setupSearchBar()
        showBottomNav()
    }

    private fun setupRecyclerView(gadgets: List<GadgetCase>?) {
        gadgetAdapter = GadgetAdapter(gadgets, this)
        binding.recViewGadget.layoutManager = GridLayoutManager(context, 1)
        binding.recViewGadget.adapter = gadgetAdapter
    }

    private fun showBottomNav() {
        val bottomNav = (activity as MainActivity).binding.bottomNav
        bottomNav.visibility = View.VISIBLE
    }

    private fun setupSearchBar() {
        binding.etSearchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                filter(s.toString())
            }
        })
    }

    private fun filter(text: String) {
        val filteredList: MutableList<GadgetCase> = ArrayList()
        for (item in allGadgets!!) {
            if (item.brand.toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT)) ||
                item.goal.toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT))) {
                filteredList.add(item)
            }
        }
        gadgetAdapter!!.filterList(filteredList)
    }

    override fun onGadgetClicked(gadget: GadgetCase) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailGadgetFragment(gadget)
        findNavController().navigate(action)
    }
}