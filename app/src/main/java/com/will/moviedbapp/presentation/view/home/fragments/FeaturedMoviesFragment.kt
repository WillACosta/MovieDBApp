package com.will.moviedbapp.presentation.view.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.will.moviedbapp.core.state.StateResult
import com.will.moviedbapp.databinding.FragmentFeaturedMoviesBinding
import com.will.moviedbapp.domain.model.Movie
import com.will.moviedbapp.presentation.model.HomeAction
import com.will.moviedbapp.presentation.view.shared.adapter.MovieAdapter
import com.will.moviedbapp.presentation.view.home.HomeViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeaturedMoviesFragment : Fragment() {

    private val binding: FragmentFeaturedMoviesBinding by lazy {
        FragmentFeaturedMoviesBinding.inflate(layoutInflater)
    }

    private val viewModel: HomeViewModel by viewModel()

    private val movieAdapter = MovieAdapter(this::onItemClicked)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        onInitFragment()
        setListeners()

        return binding.root
    }

    private fun onInitFragment() {
        viewModel.handle(HomeAction.LoadTrendingMovies)
    }

    private fun setListeners() {
        lifecycleScope.launch {
            viewModel.trendingMovies.collect { state ->
                when (state) {
                    StateResult.Loading -> {
                        handleShimmerLayout(true)
                    }

                    StateResult.Empty -> {
                        handleShimmerLayout()
                    }

                    is StateResult.Success -> {
                        binding.recyclerFeaturedMovies.apply {
                            layoutManager = LinearLayoutManager(context).apply {
                                orientation = RecyclerView.HORIZONTAL
                            }

                            adapter = movieAdapter
                        }

                        movieAdapter.submitList(state.data)
                        handleShimmerLayout()
                    }

                    is StateResult.Error -> {
                        handleShimmerLayout()
                    }
                }
            }
        }
    }

    private fun handleShimmerLayout(isVisible: Boolean = false) {
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

    private fun onItemClicked(movie: Movie) {



    }

}
