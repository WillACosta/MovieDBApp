package com.will.moviedbapp.modules.movie.presentation.genres

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.ChipGroup
import com.will.moviedbapp.core.state.Result
import com.will.moviedbapp.databinding.FragmentGenreBinding
import com.will.moviedbapp.modules.movie.domain.entity.MovieGenre
import org.koin.androidx.viewmodel.ext.android.viewModel

class GenreFragment : Fragment() {

    private val binding: FragmentGenreBinding by lazy {
        FragmentGenreBinding.inflate(layoutInflater)
    }

    private val viewModel: GenreViewModel by viewModel()
    private val genreAdapter = GenreAdapter(this::onTouchedGenreItem)

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
                    setUpRecyclerView(state.data)
                }

                else -> {}
            }
        }
    }

    private fun setUpRecyclerView(genres: List<MovieGenre>) {
        val chipGroup = ChipGroup(requireContext()).apply {
            isSingleSelection = true
        }

        binding.genresRecyclerView
            .apply {
                layoutManager = LinearLayoutManager(context).apply {
                    orientation = RecyclerView.HORIZONTAL
                    setHasFixedSize(true)
                }

                adapter = genreAdapter
            }

        genreAdapter.submitList(genres)
    }

    private fun onTouchedGenreItem(id: Int, isChecked: Boolean) {}

}
