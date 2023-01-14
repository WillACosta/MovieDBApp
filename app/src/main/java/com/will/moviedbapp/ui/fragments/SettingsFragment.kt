package com.will.moviedbapp.ui.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.will.moviedbapp.R
import com.will.moviedbapp.core.theme.OnDayNightStateChanged
import com.will.moviedbapp.databinding.FragmentSettingsBinding
import com.will.moviedbapp.ui.viewmodels.PreferencesViewModel
import com.will.moviedbapp.ui.viewmodels.SettingsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : Fragment(), OnDayNightStateChanged {

    private val binding: FragmentSettingsBinding by lazy {
        FragmentSettingsBinding.inflate(layoutInflater)
    }

    private val preferencesViewModel: PreferencesViewModel by viewModel()
    private val viewModel: SettingsViewModel by viewModel()

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

        preferencesViewModel.userData.observe(viewLifecycleOwner) {
            binding.nameContainer.text = it.name
        }

        viewModel.isDarkMode.observe(viewLifecycleOwner) { isDarkMode ->
            binding.themeButton.isChecked = isDarkMode
        }

        binding.themeButton.setOnCheckedChangeListener { _, isChecked ->
            handleDarkMode(isChecked)
        }
    }

    private fun handleDarkMode(isChecked: Boolean) {
        if (isChecked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            viewModel.handleDarkMode(true)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            viewModel.handleDarkMode(false)
        }
    }

    override fun onDayNightApplied(state: Int) {
        if (state == OnDayNightStateChanged.DAY) {
            binding.apply {
                root.setBackgroundColor(getColorFromResource(R.color.background))
                settingsTitle.setTextColor(getColorFromResource(R.color.text))
                nameTitle.setTextColor(getColorFromResource(R.color.text))
                switchTitle.setTextColor(getColorFromResource(R.color.text))

                nameContainer.chipBackgroundColor = ColorStateList.valueOf(
                    getColorFromResource(R.color.surface)
                )
                nameContainer.setTextColor(getColorFromResource(R.color.text))
                nameContainer.chipIconTint = ColorStateList.valueOf(
                    getColorFromResource(R.color.text)
                )
            }
        } else {
            binding.apply {
                root.setBackgroundColor(getColorFromResource(R.color.backgroundNight))
                settingsTitle.setTextColor(getColorFromResource(R.color.textNight))
                nameTitle.setTextColor(getColorFromResource(R.color.textNight))
                switchTitle.setTextColor(getColorFromResource(R.color.textNight))

                nameContainer.chipBackgroundColor = ColorStateList.valueOf(
                    getColorFromResource(R.color.surface)
                )
                nameContainer.setTextColor(getColorFromResource(R.color.textNight))
                nameContainer.chipIconTint = ColorStateList.valueOf(
                    getColorFromResource(R.color.textNight)
                )
            }
        }
    }

    private fun getColorFromResource(id: Int): Int {
        return ContextCompat.getColor(requireContext(), id)
    }
}
