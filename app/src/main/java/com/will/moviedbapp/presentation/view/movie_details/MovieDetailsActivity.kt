package com.will.moviedbapp.presentation.view.movie_details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.will.moviedbapp.databinding.ActivityMovieDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsActivity : AppCompatActivity() {
    private val binding: ActivityMovieDetailBinding by lazy {
        ActivityMovieDetailBinding.inflate(layoutInflater)
    }

    val id: String by lazy {
        requireNotNull(intent.extras?.getString("id")) { "movie id is required" }
    }

    private val viewModel: MovieDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
