package com.will.moviedbapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.will.moviedbapp.core.state.Result
import com.will.moviedbapp.core.utils.extensions.toChipComponent
import com.will.moviedbapp.databinding.FragmentGenreBinding
import com.will.moviedbapp.domain.entities.Movie
import com.will.moviedbapp.domain.entities.MovieGenre
import com.will.moviedbapp.ui.recyclerview.MovieAdapter
import com.will.moviedbapp.ui.viewmodels.GenreViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class GenreFragment : Fragment() {

    private val binding: FragmentGenreBinding by lazy {
        FragmentGenreBinding.inflate(layoutInflater)
    }

    private val viewModel: GenreViewModel by viewModel()
    private val movieAdapter = MovieAdapter(this::onTouchedMovieItem)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setListeners()
        return binding.root
    }

    private fun setListeners() {
        viewModel.genres.observe(viewLifecycleOwner) { state ->
            when (state) {
                Result.Loading -> {}
                Result.Empty -> {}

                is Result.Success -> {
                    setUpChipGroupView(state.data)
                }

                else -> {}
            }
        }

        viewModel.moviesByGenres.observe(viewLifecycleOwner) { state ->
            when (state) {
                Result.Loading -> {}
                Result.Empty -> {}

                is Result.Success -> {
                    setUpMoviesRecyclerView(state.data)
                }

                else -> {}
            }
        }
    }

    private fun setUpChipGroupView(genres: List<MovieGenre>) {
        binding.genresChipGroup.removeAllViews()

        genres.forEach { item ->
            binding.genresChipGroup.addView(
                item.toChipComponent(
                    requireContext(),
                    this::onTouchedGenre
                )
            )
        }
    }

    private fun setUpMoviesRecyclerView(movies: List<Movie>) {
        binding.moviesByGenresRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = movieAdapter
        }

        movieAdapter.submitList(movies)
    }

    private fun onTouchedMovieItem(movie: Movie) {}

    private fun onTouchedGenre(id: Int) {
        val selectedIds: MutableList<Int> = mutableListOf()
        selectedIds.add(id)

        val genres: Array<Int> = selectedIds.toTypedArray()
        viewModel.updateSelectedGenres(genres)
    }

}
