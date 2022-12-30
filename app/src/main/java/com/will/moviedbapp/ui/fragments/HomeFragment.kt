package com.will.moviedbapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.will.moviedbapp.R
import com.will.moviedbapp.core.constants.AppConstants
import com.will.moviedbapp.core.state.Result
import com.will.moviedbapp.databinding.FragmentHomeBinding
import com.will.moviedbapp.domain.entities.Movie
import com.will.moviedbapp.ui.recyclerview.MovieAdapter
import com.will.moviedbapp.ui.utils.MovieFunctionsHelper
import com.will.moviedbapp.ui.viewmodels.HomeViewModel
import com.will.moviedbapp.ui.viewmodels.PreferencesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    private val homeViewModel: HomeViewModel by viewModel()
    private val preferencesViewModel: PreferencesViewModel by viewModel()

    private val movieAdapter = MovieAdapter(this::onTouchedMovieItem)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setListeners()
        replaceFrameLayoutToFragments()

        return binding.root
    }

    private fun replaceFrameLayoutToFragments() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.trending_movies_frame_container, FeaturedMoviesFragment::class.java, null)
            .commit()

        parentFragmentManager.beginTransaction()
            .replace(R.id.genresFrameContainer, GenreFragment::class.java, null)
            .commit()
    }

    private fun setListeners() {
        homeViewModel.moviesResultList.observe(viewLifecycleOwner) { state ->
            when (state) {
                Result.Loading -> handleShimmerLayout(true)

                Result.Empty -> {
                    handleShimmerLayout(false)
                    binding.emptyContentComponent.root.visibility = View.VISIBLE
                }

                is Result.Error -> {
                    Toast.makeText(
                        context,
                        AppConstants.AppMessages.GENERIC_ERROR,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is Result.Success -> {
                    handleShimmerLayout(false)

                    binding.recyclerSearchedMovies.apply {
                        adapter = movieAdapter
                        layoutManager = GridLayoutManager(context, 2)
                        setHasFixedSize(true)
                    }

                    movieAdapter.submitList(state.data)
                }
            }
        }

        binding.edtSearch.doOnTextChanged { text, _, _, _ ->
            homeViewModel.searchMovies(text.toString())
        }

        homeViewModel.searchQuery.observe(viewLifecycleOwner) { query ->
            handleUiWhenIsSearchingMovie(query)
        }

        preferencesViewModel.greetingUser.observe(viewLifecycleOwner) {
            binding.greetingUser.text = it
        }
    }

    private fun onTouchedMovieItem(movie: Movie) {
        MovieFunctionsHelper.onTouchedMovieItem(activity, movie)
    }

    private fun handleUiWhenIsSearchingMovie(searchQuery: String) {
        if (searchQuery.isNotEmpty()) {
            binding.trendingMoviesFrameContainer.visibility = View.INVISIBLE
            binding.recyclerSearchedMovies.visibility = View.VISIBLE
            return
        }

        binding.trendingMoviesFrameContainer.visibility = View.VISIBLE
        binding.emptyContentComponent.root.visibility = View.GONE
        binding.recyclerSearchedMovies.visibility = View.GONE
    }

    private fun handleShimmerLayout(isVisible: Boolean) {
        if (isVisible) {
            binding.searchingShimmerLayout.apply {
                visibility = View.VISIBLE
                startShimmer()
            }

            return
        }

        binding.searchingShimmerLayout.apply {
            visibility = View.GONE
            stopShimmer()
        }
    }
}
