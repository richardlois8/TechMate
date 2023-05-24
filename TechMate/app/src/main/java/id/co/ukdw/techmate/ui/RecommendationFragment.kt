package id.co.ukdw.techmate.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import id.co.ukdw.techmate.MainActivity
import id.co.ukdw.techmate.data.database.GadgetCase
import id.co.ukdw.techmate.databinding.FragmentRecommendationBinding
import id.co.ukdw.techmate.ui.home.GadgetAdapter


class RecommendationFragment : Fragment(), GadgetAdapter.OnGadgetClickListener {
    private lateinit var binding : FragmentRecommendationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecommendationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recommendationResult = (activity as MainActivity).getEngine().getRecommendationResult()
        Log.e("RecommendationFragment", "result: $recommendationResult")

        if (recommendationResult.isNullOrEmpty()) {
            binding.imageNull.visibility = View.VISIBLE
            binding.textNull.visibility = View.VISIBLE
            binding.textNull2.visibility = View.VISIBLE
            Toast.makeText(context, "No recommendations available", Toast.LENGTH_SHORT).show()
        } else {
            binding.txtDescRecommendation.visibility = View.VISIBLE
            binding.imageNull.visibility = View.GONE
            binding.textNull.visibility = View.GONE
            binding.textNull2.visibility = View.GONE
            setupRecyclerView(recommendationResult)
        }
    }

    private fun setupRecyclerView(recommendationResult: List<GadgetCase>) {
        Log.e("RecommendationFragment", "result: $recommendationResult")
        val adapter = GadgetAdapter(recommendationResult, this)
        binding.recViewGadget.layoutManager = GridLayoutManager(context, 1)
        binding.recViewGadget.adapter = adapter
    }

    override fun onGadgetClicked(gadget: GadgetCase) {
        val action = RecommendationFragmentDirections.actionRecommendationFragmentToDetailGadgetFragment(gadget)
        findNavController().navigate(action)
    }
}