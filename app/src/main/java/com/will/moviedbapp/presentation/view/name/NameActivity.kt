package com.will.moviedbapp.presentation.view.name

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.will.moviedbapp.core.constants.AppConstants
import com.will.moviedbapp.core.utils.extensions.navigateTo
import com.will.moviedbapp.databinding.ActivityNameBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NameActivity : AppCompatActivity() {

    private val binding: ActivityNameBinding by lazy {
        ActivityNameBinding.inflate(layoutInflater)
    }

    private val _viewModel: NameViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        setListeners()

        setContentView(binding.root)
    }

    private fun setListeners() {
        binding.nextButton.setOnClickListener {
            submitUserName()
        }

        binding.edtName.doOnTextChanged { value, _, _, _ ->
            _viewModel.onNameChanged(value.toString())
        }

        _viewModel.error.observe(this) { error ->
            binding.edtContainer.error = error
            binding.nextButton.isEnabled = (error == null)
        }
    }

    private fun initView() {
        supportActionBar?.hide()
    }

    private fun submitUserName() {
        val validName = binding.edtContainer.error == null

        if (validName) {
            _viewModel.submitName()
            goToMainActivity()
        }
    }

    private fun goToMainActivity() {
        navigateTo(AppConstants.AppRoutes.HOME)
        finish()
    }
}
