package com.will.moviedbapp.presentation.view.name

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.will.moviedbapp.R
import com.will.moviedbapp.core.utils.HelperFunctions
import com.will.moviedbapp.databinding.ActivityNameBinding
import com.will.moviedbapp.presentation.view.home.HomeActivity
import com.will.moviedbapp.presentation.viewmodel.NameViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NameActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityNameBinding

    private val _viewModel: NameViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        setListeners()

        setContentView(_binding.root)
    }

    private fun setListeners() {
        _binding.nextButton.setOnClickListener {
            submitUserName()
        }

        _binding.edtName.doOnTextChanged { value, _, _, _ ->
            _viewModel.onNameChanged(value.toString())
        }

        _viewModel.error.observe(this) { error ->
            _binding.edtContainer.error = error
            _binding.nextButton.isEnabled = (error == null)
        }
    }

    private fun initView() {
        _binding = ActivityNameBinding.inflate(layoutInflater)
        supportActionBar?.hide()
    }

    private fun submitUserName() {
        val validName = _binding.edtContainer.error == null

        if (validName) {
            _viewModel.submitName()
            goToMainActivity()
            finish()
        }
    }

    private fun goToMainActivity() {
        HelperFunctions.startActivity(this, HomeActivity::class.java)
    }
}
