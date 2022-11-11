package com.will.moviedbapp.presentation.view.movie_details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.will.moviedbapp.core.constants.AppConstants
import com.will.moviedbapp.core.state.StateResult
import com.will.moviedbapp.core.utils.extensions.load
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

        setListeners()
        viewModel.getMovie(id)

        setContentView(binding.root)
    }

    private fun setListeners() {
        viewModel.movie.observe(this) { state ->
            when (state) {
                StateResult.Loading -> {}

                StateResult.Empty -> {}

                is StateResult.Error -> {}

                is StateResult.Success -> {
                    val movie = state.data

                    binding.apply {
                        movieDetailPoster.load("${AppConstants.Network.BASE_POSTER_URL}/${movie.posterPath}")
                        movieTitle.text = movie.title
                        movieDate.text = movie.releaseDate
                        movieAge.text = "12"
                        movieRating.text = movie.voteAverage.toString()
                        movieTime.text = "1h 20m"
                        movieDescription.text = movie.overview
                    }
                }
            }
        }
    }

}
