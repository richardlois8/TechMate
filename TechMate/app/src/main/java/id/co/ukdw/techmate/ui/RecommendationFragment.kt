package id.co.ukdw.techmate.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import id.co.ukdw.techmate.MainActivity
import id.co.ukdw.techmate.R
import id.co.ukdw.techmate.data.database.GadgetCase
import id.co.ukdw.techmate.databinding.FragmentRecommendationBinding
import id.co.ukdw.techmate.ui.home.GadgetAdapter


class RecommendationFragment : Fragment() {
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
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val recommendationResult = (activity as MainActivity).getEngine().getRecommendationResult()
        Log.e("RecommendationFragment", "result: $recommendationResult")
        val adapter = GadgetAdapter(recommendationResult)
        binding.recViewGadget.layoutManager = GridLayoutManager(context, 1)
        binding.recViewGadget.adapter = adapter
    }
}