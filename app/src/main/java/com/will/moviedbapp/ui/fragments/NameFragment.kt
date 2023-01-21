package com.will.moviedbapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.will.moviedbapp.databinding.FragmentNameBinding
import com.will.moviedbapp.ui.viewmodels.NameViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class NameFragment : Fragment() {

    private val binding: FragmentNameBinding by lazy {
        FragmentNameBinding.inflate(layoutInflater)
    }

    private val viewModel: NameViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setListeners()
        return binding.root
    }

    private fun setListeners() {
        binding.nextButton.setOnClickListener { onSubmit() }

        binding.edtName.doOnTextChanged { value, _, _, _ ->
            viewModel.onNameChanged(value.toString())
        }

        lifecycleScope.launch {
            viewModel.nameError.collect {
                binding.edtContainer.error = it
                binding.nextButton.isEnabled = it == null
            }
        }
    }

    private fun onSubmit() {
        val validName = binding.edtContainer.error == null

        if (validName) {
            viewModel.submitName()
            navigateToHomeRoute()
        }
    }

    private fun navigateToHomeRoute() {
        findNavController().navigate(
            NameFragmentDirections.actionNameFragmentToHomeFragment()
        )
    }
}
