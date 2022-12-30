package com.will.moviedbapp.ui.activities

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.will.moviedbapp.R
import com.will.moviedbapp.core.theme.OnDayNightStateChanged
import com.will.moviedbapp.databinding.ActivityMainBinding
import com.will.moviedbapp.ui.fragments.HomeFragment
import com.will.moviedbapp.ui.fragments.SettingsFragment
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

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO -> applyDayNight(OnDayNightStateChanged.DAY)
            Configuration.UI_MODE_NIGHT_YES -> applyDayNight(OnDayNightStateChanged.NIGHT)
        }
    }

    private fun applyDayNight(state: Int) {
        if (state == OnDayNightStateChanged.DAY) {
            binding.bottomNavigation.setBackgroundColor(
                ContextCompat.getColor(this, R.color.background)
            )
        } else {
            binding.bottomNavigation.setBackgroundColor(
                ContextCompat.getColor(this, R.color.backgroundNight)
            )
        }

        supportFragmentManager.fragments.forEach {
            if (it is OnDayNightStateChanged) {
                it.onDayNightApplied(state)
            }
        }
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
