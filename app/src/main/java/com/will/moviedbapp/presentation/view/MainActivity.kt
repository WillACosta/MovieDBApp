package com.will.moviedbapp.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.will.moviedbapp.R
import com.will.moviedbapp.databinding.ActivityMainBinding
import com.will.moviedbapp.presentation.view.home.HomeFragment
import com.will.moviedbapp.presentation.view.settings.SettingsFragment
import org.koin.androidx.fragment.android.setupKoinFragmentFactory

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        setupKoinFragmentFactory()
        handleFragments()

        setContentView(binding.root)
    }

    private fun handleFragments() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_container, HomeFragment::class.java, null)
            .commit()

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_menu_item -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_container, HomeFragment::class.java, null)
                        .commit()

                    true
                }

                R.id.settings_menu_item -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_container, SettingsFragment::class.java, null)
                        .commit()

                    true
                }

                else -> false
            }
        }
    }

}
