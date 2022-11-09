package com.will.moviedbapp.presentation.view.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.will.moviedbapp.R
import com.will.moviedbapp.core.state.StateResult
import com.will.moviedbapp.databinding.ActivityHomeBinding
import com.will.moviedbapp.presentation.model.HomeAction
import com.will.moviedbapp.presentation.view.home.fragments.FeaturedMoviesFragment
import com.will.moviedbapp.presentation.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val viewModel: HomeViewModel by viewModel()

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
                StateResult.Loading -> {}

                StateResult.Empty -> {
                    binding.emptyContentComponent.root.visibility = View.VISIBLE
                }

                is StateResult.Error -> {}

                is StateResult.Success -> {}
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
    }

    private fun initView() {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        supportActionBar?.hide()
    }

    private fun handleUiWhenIsSearchingMovie(isSearching: Boolean = false) {
        if (isSearching) {
            binding.trendingMoviesFrameContainer.visibility = View.INVISIBLE

            return
        }

        binding.emptyContentComponent.root.visibility = View.GONE
        binding.trendingMoviesFrameContainer.visibility = View.VISIBLE
    }

}
