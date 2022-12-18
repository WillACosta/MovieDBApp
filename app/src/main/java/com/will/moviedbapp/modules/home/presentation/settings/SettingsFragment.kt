package com.will.moviedbapp.modules.home.presentation.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.will.moviedbapp.core.utils.extensions.setDarkMode
import com.will.moviedbapp.databinding.FragmentSettingsBinding
import com.will.moviedbapp.modules.home.presentation.settings.fragments.UpdateNameFragment
import com.will.moviedbapp.modules.shared.presentation.PreferencesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : Fragment() {

    private val binding: FragmentSettingsBinding by lazy {
        FragmentSettingsBinding.inflate(layoutInflater)
    }

    private val preferencesViewModel: PreferencesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setListeners()

        return binding.root
    }

    private fun setListeners() {
        binding.nameContainer.setOnClickListener {
            UpdateNameFragment()
                .show(
                    childFragmentManager, UpdateNameFragment.TAG
                )
        }

        preferencesViewModel.userPreferences.observe(viewLifecycleOwner) {
            binding.nameContainer.text = it.name

            if (it.isDarkMode) {
                binding.themeButton.isChecked = true
            }
        }

        binding.themeButton.setOnCheckedChangeListener { _, isChecked ->
            handleDarkMode(isChecked)
        }
    }

    private fun handleDarkMode(checked: Boolean) {
        if (checked) {
            requireActivity().setDarkMode()
            preferencesViewModel.updateIsDarkMode(true)
        } else {
            requireActivity().setDarkMode(false)
            preferencesViewModel.updateIsDarkMode(false)
        }
    }
}
