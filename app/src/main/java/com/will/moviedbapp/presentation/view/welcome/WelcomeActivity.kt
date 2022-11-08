package com.will.moviedbapp.presentation.view.welcome

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.will.moviedbapp.core.utils.HelperFunctions
import com.will.moviedbapp.databinding.ActivityWelcomeBinding
import com.will.moviedbapp.presentation.view.home.HomeActivity
import com.will.moviedbapp.presentation.view.name.NameActivity

class WelcomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityWelcomeBinding

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
            HelperFunctions.startActivity(this, NameActivity::class.java)
        }
    }
}
