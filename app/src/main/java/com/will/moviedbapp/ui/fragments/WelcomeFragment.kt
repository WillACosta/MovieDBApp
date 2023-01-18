package com.will.moviedbapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.will.moviedbapp.R
import com.will.moviedbapp.core.constants.AppRoutes
import com.will.moviedbapp.core.utils.extensions.navigateTo
import com.will.moviedbapp.core.utils.extensions.setFullscreen
import com.will.moviedbapp.databinding.FragmentWelcomeBinding
import com.will.moviedbapp.ui.viewmodels.WelcomeViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class WelcomeFragment : Fragment() {

    private val binding: FragmentWelcomeBinding by lazy {
        FragmentWelcomeBinding.inflate(layoutInflater)
    }

    private val viewModel: WelcomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        onInitView()
        setListeners()

        return binding.root
    }

    private fun onInitView() {
        requireActivity().setFullscreen()
    }

    private fun setListeners() {
        binding.buttonStart.setOnClickListener {
            viewModel.hideWelcome()
        }

        lifecycleScope.launch {
            viewModel.userPreferences
                .collect {
                    if (it.shouldHideWelcome && it.userName.isEmpty()) {
                        navigateToNameRoute()
                    }

                    if (it.shouldHideWelcome && it.userName.isNotEmpty()) {
                        navigateToHomeRoute()
                    }
                }
        }
    }

    private fun navigateToNameRoute() {
        WelcomeFragmentDirections.actionWelcomeFragmentToNameFragment()
    }

    private fun navigateToHomeRoute() {
        WelcomeFragmentDirections.actionWelcomeFragmentToHomeFragment()
    }

}
