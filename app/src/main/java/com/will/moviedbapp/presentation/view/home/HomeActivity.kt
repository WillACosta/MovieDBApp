package com.will.moviedbapp.presentation.view.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.GridLayoutManager
import com.will.moviedbapp.R
import com.will.moviedbapp.core.constants.AppConstants
import com.will.moviedbapp.core.state.StateResult
import com.will.moviedbapp.core.utils.extensions.navigateTo
import com.will.moviedbapp.databinding.ActivityHomeBinding
import com.will.moviedbapp.domain.model.Movie
import com.will.moviedbapp.presentation.model.HomeAction
import com.will.moviedbapp.presentation.view.adapter.MovieAdapter
import com.will.moviedbapp.presentation.view.home.fragments.FeaturedMoviesFragment
import com.will.moviedbapp.presentation.viewmodel.PreferencesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val viewModel: HomeViewModel by viewModel()
    private val preferencesViewModel: PreferencesViewModel by viewModel()

    private val movieAdapter = MovieAdapter(this::onItemClicked)

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

    private fun setListeners() {
        viewModel.movies.observe(this) { state ->
            when (state) {
                StateResult.Loading -> handleIsLoading(true)

                StateResult.Empty -> binding.emptyContentComponent.root.visibility = View.VISIBLE

                is StateResult.Error -> {}

                is StateResult.Success -> {
                    handleIsLoading()
                    binding.recyclerSearchedMovies.visibility = View.VISIBLE

                    binding.recyclerSearchedMovies.apply {
                        adapter = movieAdapter
                        layoutManager = GridLayoutManager(this@HomeActivity, 2)
                    }

                    movieAdapter.submitList(state.data)
                }
            }
        }

        viewModel.searchQuery.observe(this) { query ->
            if (query.isNotEmpty()) {
                handleUiWhenIsSearchingMovie(true)
            } else {
                handleUiWhenIsSearchingMovie()
            }
        }

        binding.edtSearch.doOnTextChanged { text, _, _, _ ->
            viewModel.handle(HomeAction.SearchMovies(text.toString()))
        }

        preferencesViewModel.userPreferences.observe(this) { prefs ->
            binding.greetingUser.text = "Hello, ${prefs.name}!"
        }

        viewModel.navigateEvent.observe(this) { movie ->
            val extras = Bundle().apply { putString("id", movie.id.toString()) }
            navigateTo(AppConstants.AppRoutes.MOVIE_DETAIL, extras)
        }
    }

    private fun onItemClicked(movie: Movie) {
        viewModel.handle(HomeAction.ViewMovieDetails(movie))
    }

    private fun initView() {
        binding = ActivityHomeBinding.inflate(layoutInflater)

        supportActionBar?.hide()
        preferencesViewModel.getPreferences()
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
