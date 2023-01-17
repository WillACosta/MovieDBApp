package com.will.moviedbapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.will.moviedbapp.core.constants.AppConstants
import com.will.moviedbapp.core.utils.extensions.load
import com.will.moviedbapp.data.utils.Resource
import com.will.moviedbapp.databinding.FragmentMediaDetailsBinding
import com.will.moviedbapp.ui.viewmodels.MovieDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MediaDetails : Fragment() {

    private val binding: FragmentMediaDetailsBinding by lazy {
        FragmentMediaDetailsBinding.inflate(layoutInflater)
    }

    private val mediaID: Int = 0

    private val viewModel: MovieDetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel.getMovie(mediaID.toString())
        return binding.root
    }

    private fun setListeners() {
        viewModel.movie.observe(this) { state ->
            when (state) {
                Resource.Loading -> {}

                Resource.Empty -> {}

                is Resource.Error -> {}

                is Resource.Success -> {
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

        binding.topAppBar.setNavigationOnClickListener {}
    }

    private fun handleRuntimeMovie(runtime: Int?): String {
        if (runtime == null) return "-"

        val time = runtime / 60.0
        return String.format("%.2fhr", time)
    }

}
