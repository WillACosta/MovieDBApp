package com.will.moviedbapp.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.will.moviedbapp.core.constants.AppRoutes
import com.will.moviedbapp.core.utils.extensions.navigateTo
import com.will.moviedbapp.core.utils.extensions.setFullscreen
import com.will.moviedbapp.databinding.ActivityWelcomeBinding
import com.will.moviedbapp.ui.viewmodels.WelcomeViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class WelcomeActivity : AppCompatActivity() {

    private val binding: ActivityWelcomeBinding by lazy {
        ActivityWelcomeBinding.inflate(layoutInflater)
    }

    private val viewModel: WelcomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        onInitView()
        setListeners()
    }

    private fun onInitView() {
        setFullscreen()
        supportActionBar?.hide()
    }

    private fun setListeners() {
        binding.buttonStart.setOnClickListener {
            viewModel.hideWelcome()
        }

        lifecycleScope.launch {
            viewModel.userPreferences
                .collect {
                    if (it.shouldHideWelcome && it.userName.isEmpty()) {
                        goToNameActivity()
                    }

                    if (it.shouldHideWelcome && it.userName.isNotEmpty()) {
                        gotoHomeActivity()
                    }
                }
        }
    }

    private fun goToNameActivity() {
        navigateTo(AppRoutes.NAME)
        finish()
    }

    private fun gotoHomeActivity() {
        navigateTo(AppRoutes.MAIN)
        finish()
    }
}
