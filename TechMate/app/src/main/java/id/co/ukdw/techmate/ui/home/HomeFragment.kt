package id.co.ukdw.techmate.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import id.co.ukdw.techmate.MainActivity
import id.co.ukdw.techmate.R
import id.co.ukdw.techmate.data.database.GadgetCase
import id.co.ukdw.techmate.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), GadgetAdapter.OnGadgetClickListener {
    private lateinit var binding: FragmentHomeBinding
    private var gadgetAdapter: GadgetAdapter? = null
    private var allGadgets: List<GadgetCase>? = null
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.fabHelp.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_helpFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        allGadgets = (activity as MainActivity).getEngine().getAllGadget()
        setupRecyclerView(allGadgets)

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        homeViewModel.getSearchResultLiveData().observe(viewLifecycleOwner) { filteredList ->
            gadgetAdapter?.filterList(filteredList)
        }

        setupSearchBar()
        showBottomNav()
    }

    private fun setupRecyclerView(gadgets: List<GadgetCase>?) {
        gadgetAdapter = GadgetAdapter(gadgets, this, false)
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
                homeViewModel.filter(allGadgets ?: emptyList(), s.toString())
            }
        })
    }

    override fun onGadgetClicked(gadget: GadgetCase) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailGadgetFragment(gadget)
        findNavController().navigate(action)
    }
}