package com.will.moviedbapp.presentation.view.home

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
import com.will.moviedbapp.core.state.StateResult
import com.will.moviedbapp.core.utils.extensions.navigateTo
import com.will.moviedbapp.databinding.FragmentHomeBinding

import com.will.moviedbapp.domain.model.Movie
import com.will.moviedbapp.presentation.model.HomeAction
import com.will.moviedbapp.presentation.view.home.fragments.FeaturedMoviesFragment
import com.will.moviedbapp.presentation.view.shared.PreferencesViewModel
import com.will.moviedbapp.presentation.view.shared.adapter.MovieAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    private val viewModel: HomeViewModel by viewModel()
    private val preferencesViewModel: PreferencesViewModel by viewModel()

    private val movieAdapter = MovieAdapter(this::onItemClicked)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setListeners()
        handleFragments()

        return binding.root
    }

    private fun handleFragments() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.trending_movies_frame_container, FeaturedMoviesFragment::class.java, null)
            .commit()
    }

    private fun setListeners() {
        viewModel.movies.observe(viewLifecycleOwner) { state ->
            when (state) {
                StateResult.Loading -> handleIsLoading(true)

                StateResult.Empty -> {
                    handleIsLoading()
                    binding.emptyContentComponent.root.visibility = View.VISIBLE
                }

                is StateResult.Error -> {
                    Toast.makeText(
                        context,
                        AppConstants.AppMessages.GENERIC_ERROR,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is StateResult.Success -> {
                    handleIsLoading()
                    binding.recyclerSearchedMovies.visibility = View.VISIBLE

                    binding.recyclerSearchedMovies.apply {
                        adapter = movieAdapter
                        layoutManager = GridLayoutManager(context, 2)
                    }

                    movieAdapter.submitList(state.data)
                }
            }
        }

        viewModel.searchQuery.observe(viewLifecycleOwner) { query ->
            if (query.isNotEmpty()) {
                handleUiWhenIsSearchingMovie(true)
            } else {
                handleUiWhenIsSearchingMovie()
            }
        }

        binding.edtSearch.doOnTextChanged { text, _, _, _ ->
            viewModel.handle(HomeAction.SearchMovies(text.toString()))
        }

        preferencesViewModel.userPreferences.observe(viewLifecycleOwner) { prefs ->
            binding.greetingUser.text = "Hello, ${prefs.name}!"
        }

        viewModel.navigateEvent.observe(viewLifecycleOwner) { movie ->
            val extras = Bundle().apply { putString("id", movie.id.toString()) }
            activity?.navigateTo(AppConstants.AppRoutes.MOVIE_DETAIL, extras)
        }
    }

    private fun onItemClicked(movie: Movie) {
        viewModel.handle(HomeAction.ViewMovieDetails(movie))
    }

    private fun handleUiWhenIsSearchingMovie(isSearching: Boolean = false) {
        if (isSearching) {
            binding.trendingMoviesFrameContainer.visibility = View.INVISIBLE

            return
        }

        binding.trendingMoviesFrameContainer.visibility = View.VISIBLE
        binding.emptyContentComponent.root.visibility = View.GONE
        binding.recyclerSearchedMovies.visibility = View.GONE
    }

    private fun handleIsLoading(isLoading: Boolean = false) {
        if (isLoading) {
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
