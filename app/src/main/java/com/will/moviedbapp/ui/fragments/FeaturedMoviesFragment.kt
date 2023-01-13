package com.will.moviedbapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.will.moviedbapp.databinding.FragmentFeaturedMoviesBinding
import com.will.moviedbapp.domain.entities.Movie
import com.will.moviedbapp.ui.recyclerview.MovieAdapter
import com.will.moviedbapp.ui.utils.MovieFunctionsHelper
import com.will.moviedbapp.ui.viewmodels.FeaturedMoviesViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeaturedMoviesFragment : Fragment() {

    private val binding: FragmentFeaturedMoviesBinding by lazy {
        FragmentFeaturedMoviesBinding.inflate(layoutInflater)
    }

    private val viewModel: FeaturedMoviesViewModel by viewModel()
    private val movieAdapter = MovieAdapter(this::onTouchedMovieItem)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setListeners()
        viewModel.getTrendingMovies()

        return binding.root
    }

    private fun setListeners() {
        lifecycleScope.launch {
//            viewModel.moviesList.observe(viewLifecycleOwner) { state ->
//                when (state) {
//                    Resource.Loading -> handleShimmerLayout(true)
//
//                    Resource.Empty -> handleShimmerLayout(false)
//
//                    is Resource.Success -> {
//                        binding.recyclerFeaturedMovies.apply {
//                            layoutManager = LinearLayoutManager(context).apply {
//                                orientation = RecyclerView.HORIZONTAL
//                            }
//
//                            adapter = movieAdapter
//                        }
//
//                        movieAdapter.submitList(state.data)
//                        handleShimmerLayout(false)
//                    }
//
//                    is Resource.Error -> handleShimmerLayout(false)
//                }
//            }
        }
    }

    private fun handleShimmerLayout(isVisible: Boolean) {
        if (isVisible) {
            binding.shimmerLayout.apply {
                startShimmer()
                visibility = View.VISIBLE
            }

            return
        }

        binding.shimmerLayout.apply {
            stopShimmer()
            visibility = View.GONE
        }
    }

    private fun onTouchedMovieItem(movie: Movie) {
        MovieFunctionsHelper.onTouchedMovieItem(activity, movie)
    }
}
