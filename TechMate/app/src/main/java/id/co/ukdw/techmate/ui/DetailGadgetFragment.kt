package id.co.ukdw.techmate.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import id.co.ukdw.techmate.R
import id.co.ukdw.techmate.databinding.FragmentDetailGadgetBinding


class DetailGadgetFragment : Fragment() {
    private var _binding: FragmentDetailGadgetBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailGadgetBinding.inflate(inflater, container, false)

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gadgetCase = DetailGadgetFragmentArgs.fromBundle(requireArguments()).gadgetCase

        binding.txtTitleRecommendation.text = gadgetCase.goal
        Glide.with(this)
            .load(gadgetCase.image)
            .into(binding.detailImg)
        binding.detailDesc.text = gadgetCase.desc
        binding.memoriValue.text = gadgetCase.memory.toString()
        binding.ramValue.text = gadgetCase.ram.toString()
        binding.price.text = gadgetCase.price.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}