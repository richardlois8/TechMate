package id.co.ukdw.techmate.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import id.co.ukdw.techmate.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {
    private lateinit var binding: FragmentAboutBinding
    private lateinit var viewModel: AboutViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this)[AboutViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = UserAdapter(mutableListOf())
        binding.recViewUser.layoutManager = GridLayoutManager(context, 1)
        binding.recViewUser.adapter = adapter

        viewModel.setUsers()

        viewModel.getUsersLiveData().observe(viewLifecycleOwner) { users ->
            users?.let {
                (binding.recViewUser.adapter as UserAdapter).updateData(it)
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (isEnabled && this@AboutFragment.isVisible) {
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






