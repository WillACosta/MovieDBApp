package com.will.moviedbapp.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.will.moviedbapp.core.constants.AppConstants
import com.will.moviedbapp.core.state.Result
import com.will.moviedbapp.core.utils.extensions.load
import com.will.moviedbapp.databinding.ActivityMovieDetailBinding
import com.will.moviedbapp.ui.viewmodels.MovieDetailsViewModel
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
                Result.Loading -> {}

                Result.Empty -> {}

                is Result.Error -> {}

                is Result.Success -> {
                    val movie = state.data

                    binding.apply {
                        movieDetailPoster.load(
                            "${AppConstants.Network.BASE_POSTER_URL}/${movie.posterPath}"
                        )
                        movieTitle.text = movie.title
                        movieDate.text = movie.releaseDate
                        movieAge.text = "12"
                        movieRating.text = String.format("%.2f", movie.voteAverage)
                        movieTime.text = handleRuntimeMovie(movie.runtime)
                        movieDescription.text = movie.overview
                    }
                }
            }
        }

        binding.topAppBar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun handleRuntimeMovie(runtime: Int?): String {
        if (runtime == null) return "-"

        val time = runtime / 60.0
        return String.format("%.2fhr", time)
    }
}
