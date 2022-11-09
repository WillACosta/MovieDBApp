package com.will.moviedbapp.presentation.view.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.will.moviedbapp.R
import com.will.moviedbapp.databinding.ActivityHomeBinding
import com.will.moviedbapp.presentation.view.home.fragments.FeaturedMoviesFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        setListeners()
        handleFragments()

        setContentView(binding.root)
    }

    private fun handleFragments() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.trending_movies_frame_container, FeaturedMoviesFragment::class.java, null)
            .commit()
    }

    private fun setListeners() {}

    private fun initView() {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        supportActionBar?.hide()
    }

}
