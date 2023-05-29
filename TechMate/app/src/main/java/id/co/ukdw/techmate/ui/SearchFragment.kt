package id.co.ukdw.techmate.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import id.co.ukdw.techmate.MainActivity
import id.co.ukdw.techmate.R
import id.co.ukdw.techmate.databinding.FragmentSearchBinding
import id.co.ukdw.techmate.utils.NumberTextWatcherForThousand

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        (activity as MainActivity).getEngine().clearRecommendationResult()
    }

    private fun setListener() {
        /**
        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                binding.btnFindRecommendation.isEnabled = fieldIsNotEmpty()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

        binding.etBrand.addTextChangedListener(textWatcher)
        binding.etMemory.addTextChangedListener(textWatcher)
        binding.etRam.addTextChangedListener(textWatcher)
        binding.etPrice.addTextChangedListener(textWatcher)
        binding.etFeatures.addTextChangedListener(textWatcher)
        binding.btnFindRecommendation.isEnabled = fieldIsNotEmpty()

        binding.btnFindRecommendation.isEnabled = false
        */

        binding.etPrice.addTextChangedListener(NumberTextWatcherForThousand(binding.etPrice))
        NumberTextWatcherForThousand.trimCommaOfString(binding.etPrice.text.toString())

        binding.btnFindRecommendation.setOnClickListener {
            val brand = binding.etBrand.text.toString()
            val memory = binding.etMemory.text.toString()
            val memoryInt = if (memory == "") -1 else memory.toInt()
            val ram = binding.etRam.text.toString()
            val ramInt = if (ram == "") -1 else ram.toInt()
            var priceString = binding.etPrice.text.toString()
            if (priceString.contains(",")) priceString = priceString.replace(",", "")
            val priceInt = if (priceString == "") -1 else priceString.toInt()
            val features = binding.etFeatures.text.toString()

            /**
            if (fieldIsNotEmpty()) {
                val mapInput = mapOf(
                    "brand" to brand,
                    "memory" to memory,
                    "ram" to ram,
                    "price" to priceInt,
                    "features" to features
                )
                (activity as MainActivity).getEngine().recommendation(mapInput)
                view?.findNavController()
                    ?.navigate(R.id.action_searchFragment_to_recommendationFragment)
            }
            */

            val mapInput = mapOf(
                "brand" to brand,
                "memory" to memoryInt,
                "ram" to ramInt,
                "price" to priceInt,
                "features" to features
            )
            (activity as MainActivity).getEngine().recommendation(mapInput)
            view?.findNavController()
                ?.navigate(R.id.action_searchFragment_to_recommendationFragment)
        }
    }

    private fun fieldIsNotEmpty(): Boolean {
        var status = true
        if (binding.etBrand.text!!.isEmpty()) {
            binding.etBrand.error = "Brand cannot be empty"
            status = false
        }
        if (binding.etMemory.text!!.isEmpty()) {
            binding.etMemory.error = "Memory cannot be empty"
            status = false
        }
        if (binding.etRam.text!!.isEmpty()) {
            binding.etRam.error = "RAM cannot be empty"
            status = false
        }
        if (binding.etPrice.text!!.isEmpty()) {
            binding.etPrice.error = "Price cannot be empty"
            status = false
        }
        if (binding.etFeatures.text!!.isEmpty()) {
            binding.etFeatures.error = "Features cannot be empty"
            status = false
        }
        return status
    }
}