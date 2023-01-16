package com.will.moviedbapp.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import com.will.moviedbapp.core.constants.AppRoutes
import com.will.moviedbapp.core.utils.extensions.navigateTo
import com.will.moviedbapp.databinding.ActivityNameBinding
import com.will.moviedbapp.ui.viewmodels.NameViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class NameActivity : AppCompatActivity() {

    private val binding: ActivityNameBinding by lazy {
        ActivityNameBinding.inflate(layoutInflater)
    }

    private val viewModel: NameViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
        setListeners()
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

    private fun initView() {
        supportActionBar?.hide()
    }

    private fun onSubmit() {
        val validName = binding.edtContainer.error == null

        if (validName) {
            viewModel.submitName()
            navigateToMainActivity()
        }
    }

    private fun navigateToMainActivity() {
        navigateTo(AppRoutes.MAIN)
        finish()
    }
}
