package com.will.moviedbapp.presentation.view.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.will.moviedbapp.core.state.StateResult
import com.will.moviedbapp.databinding.ActivityHomeBinding
import com.will.moviedbapp.presentation.model.HomeAction
import com.will.moviedbapp.presentation.view.adapter.MovieAdapter
import com.will.moviedbapp.presentation.viewmodel.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val viewModel by viewModel<HomeViewModel>()

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        setListeners()

        setContentView(binding.root)
    }

    private fun setListeners() {
        lifecycleScope.launch(Dispatchers.Default) {
            viewModel.trendingMovies.collect { state ->
                when (state) {
                    StateResult.Loading -> {}
                    StateResult.Empty -> {}

                    is StateResult.Success -> {
                        binding.recyclerFeaturedMovies.adapter = MovieAdapter(state.data)
                    }

                    is StateResult.Error -> {}
                }
            }
        }
    }

    private fun initView() {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        supportActionBar?.hide()

        viewModel.handle(HomeAction.LoadTrendingMovies)
    }

}
