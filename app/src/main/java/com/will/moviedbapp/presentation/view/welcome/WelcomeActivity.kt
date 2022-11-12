package com.will.moviedbapp.presentation.view.welcome

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.will.moviedbapp.core.constants.AppConstants
import com.will.moviedbapp.core.utils.extensions.navigateTo
import com.will.moviedbapp.databinding.ActivityWelcomeBinding
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
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        supportActionBar?.hide()
    }

    private fun setListeners() {
        binding.buttonStart.setOnClickListener {
            viewModel.handleFirstAccess()
        }

        viewModel.userPreferences.observe(this) { prefs ->

            if (prefs.isNotFirsAccess && prefs.name.isEmpty()) {
                goToNameActivity()
            }

            if (prefs.isNotFirsAccess && prefs.name.isNotEmpty()) {
                gotoHomeActivity()
            }
        }

    }

    private fun goToNameActivity() {
        navigateTo(AppConstants.AppRoutes.NAME)
        finish()
    }

    private fun gotoHomeActivity() {
        navigateTo(AppConstants.AppRoutes.HOME)
        finish()
    }
}
