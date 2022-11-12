package com.will.moviedbapp.presentation.view.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.will.moviedbapp.core.constants.AppConstants
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
        binding.edtName.doOnTextChanged { value, _, _, _ ->
            nameViewModel.onNameChanged(value.toString())
        }

        nameViewModel.error.observe(viewLifecycleOwner) { error ->
            binding.edtContainer.error = error
        }

        preferencesViewModel.userPreferences.observe(viewLifecycleOwner) { prefs ->
            binding.edtName.setText(prefs.name)
        }

        parentFragmentManager.addOnBackStackChangedListener {
            submitUserName()
        }
    }

    private fun submitUserName() {
        val validName = binding.edtContainer.error == null

        if (validName) {
            nameViewModel.submitName()
            Toast.makeText(
                context,
                AppConstants.AppMessages.NAME_EDIT_SUCCESS,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}
