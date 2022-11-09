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
import com.will.moviedbapp.presentation.model.HomeAction
import com.will.moviedbapp.presentation.view.adapter.MovieAdapter
import com.will.moviedbapp.presentation.viewmodel.HomeViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeaturedMoviesFragment : Fragment() {

    private val binding: FragmentFeaturedMoviesBinding by lazy {
        FragmentFeaturedMoviesBinding.inflate(layoutInflater)
    }

    private val viewModel: HomeViewModel by viewModel()

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
                    StateResult.Loading -> {}
                    StateResult.Empty -> {}

                    is StateResult.Success -> {
                        binding.recyclerFeaturedMovies.layoutManager =
                            LinearLayoutManager(context).apply {
                                orientation = RecyclerView.HORIZONTAL
                            }

                        binding.recyclerFeaturedMovies.adapter = MovieAdapter(state.data)
                    }

                    is StateResult.Error -> {}
                }
            }
        }
    }

}