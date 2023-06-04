package id.co.ukdw.techmate.ui.insert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import id.co.ukdw.techmate.R
import id.co.ukdw.techmate.data.database.GadgetCase
import id.co.ukdw.techmate.databinding.FragmentInsertBinding
import id.co.ukdw.techmate.engine.CBREngine
import id.co.ukdw.techmate.utils.NumberTextWatcherForThousand
import kotlinx.coroutines.launch

class InsertFragment : Fragment() {
    private var _binding: FragmentInsertBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInsertBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cbrEngine = CBREngine(requireContext())

        binding.etPrice.addTextChangedListener(NumberTextWatcherForThousand(binding.etPrice))
        NumberTextWatcherForThousand.trimCommaOfString(binding.etPrice.text.toString())

        binding.btnFindRecommendation.setOnClickListener {
            val brand = binding.etBrand.text.toString()
            val ramString = binding.etRam.text.toString()
            val memoryString = binding.etMemory.text.toString()
            val priceString = binding.etPrice.text.toString().replace(",", "")
            val features = binding.etFeatures.text.toString()
            val image = binding.etImage.text.toString()
            val goal = binding.etGoal.text.toString()
            val desc = binding.etDesc.text.toString()

            if(brand.isBlank() || ramString.isBlank() || memoryString.isBlank() || priceString.isBlank() || features.isBlank() || image.isBlank() || goal.isBlank() || desc.isBlank()) {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                val ram = ramString.toInt()
                val memory = memoryString.toInt()
                val price = priceString.toInt()

                val gadgetCase = GadgetCase(0, brand, memory, ram, price, features, image, goal, desc, 0.0)
                lifecycleScope.launch {
                    try {
                        cbrEngine.insertCase(gadgetCase)
                        Toast.makeText(requireContext(), "Insert successful", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_insertFragment_to_homeFragment)
                    } catch (e: Exception) {
                        Toast.makeText(requireContext(), "Insert failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (isEnabled && this@InsertFragment.isVisible) {
                        isEnabled = false
                        requireActivity().finish() // Close the app if the current fragment is the HomeFragment
                    } else {
                        isEnabled = true
                        requireActivity().onBackPressed() // Default back button functionality
                    }
                }
            })
    }
}