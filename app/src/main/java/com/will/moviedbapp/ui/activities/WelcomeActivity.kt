package com.will.moviedbapp.ui.activities

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.will.moviedbapp.core.constants.AppRoutes
import com.will.moviedbapp.core.utils.extensions.navigateTo
import com.will.moviedbapp.databinding.ActivityWelcomeBinding
import com.will.moviedbapp.ui.viewmodels.WelcomeViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class WelcomeActivity : AppCompatActivity() {

    private val binding: ActivityWelcomeBinding by lazy {
        ActivityWelcomeBinding.inflate(layoutInflater)
    }

    private val viewModel: WelcomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onInitView()
        setListeners()

        setContentView(binding.root)
    }

    private fun onInitView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        supportActionBar?.hide()
    }

    private fun setListeners() {
        binding.buttonStart.setOnClickListener {
            viewModel.setUserFirstTime()
        }

        lifecycleScope.launch {
            viewModel.userPreferences
                .collect {
                    if (!it.isFirstAccess && it.userName.isEmpty()) {
                        goToNameActivity()
                    }

                    if (!it.isFirstAccess && it.userName.isNotEmpty()) {
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
