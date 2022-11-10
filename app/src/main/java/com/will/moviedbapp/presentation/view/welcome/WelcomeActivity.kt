package com.will.moviedbapp.presentation.view.welcome

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.will.moviedbapp.core.utils.HelperFunctions
import com.will.moviedbapp.databinding.ActivityWelcomeBinding
import com.will.moviedbapp.presentation.view.home.HomeActivity
import com.will.moviedbapp.presentation.view.name.NameActivity
import com.will.moviedbapp.presentation.viewmodel.WelcomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding
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

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
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
        HelperFunctions.startActivity(this, NameActivity::class.java)
    }

    private fun gotoHomeActivity() {
        HelperFunctions.startActivity(this, HomeActivity::class.java)
    }
}
