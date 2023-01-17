package com.will.moviedbapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.will.moviedbapp.R
import com.will.moviedbapp.databinding.ActivityMainBinding
import org.koin.androidx.fragment.android.setupKoinFragmentFactory

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpView()
        setListeners()
        setupKoinFragmentFactory()

        setContentView(binding.root)
    }

    private fun setUpView() {
        binding = ActivityMainBinding.inflate(layoutInflater)

        val navHost = supportFragmentManager.findFragmentById(NAV_HOST_ID) as NavHostFragment

        navController = navHost.navController
        binding.bottomNavigationBar.setupWithNavController(navController)
        supportActionBar?.hide()
    }

    private fun setListeners() {
        navController.addOnDestinationChangedListener { _, _, args ->
            binding.bottomNavigationBar.isVisible =
                args?.getBoolean(
                    "showBottomNavigationBar",
                    false
                ) == true
        }
    }

    companion object {
        const val NAV_HOST_ID = R.id.nav_host_fragment
    }
}
