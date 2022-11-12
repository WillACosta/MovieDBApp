package com.will.moviedbapp.presentation.view.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.will.moviedbapp.databinding.FragmentSettingsBinding
import com.will.moviedbapp.presentation.view.name.NameViewModel
import com.will.moviedbapp.presentation.view.shared.PreferencesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : Fragment() {

    private val binding: FragmentSettingsBinding by lazy {
        FragmentSettingsBinding.inflate(layoutInflater)
    }

    private val nameViewModel: NameViewModel by viewModel()
    private val preferencesViewModel: PreferencesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        preferencesViewModel.getPreferences()
        setListeners()

        return binding.root
    }

    private fun setListeners() {

    }

    private fun submitUserName() {

    }

}