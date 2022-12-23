package com.will.moviedbapp.modules.movie.presentation.genres

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.will.moviedbapp.core.state.Result
import com.will.moviedbapp.core.utils.extensions.toChipComponent
import com.will.moviedbapp.databinding.FragmentGenreBinding
import com.will.moviedbapp.modules.movie.domain.entity.MovieGenre
import org.koin.androidx.viewmodel.ext.android.viewModel

class GenreFragment : Fragment() {

    private val binding: FragmentGenreBinding by lazy {
        FragmentGenreBinding.inflate(layoutInflater)
    }

    private val viewModel: GenreViewModel by viewModel()

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

        binding.genresChipGroup.setOnCheckedStateChangeListener { _, checkedIds ->
            if (checkedIds.isNotEmpty()) {
                print(checkedIds.toString())
            }
        }
    }

    private fun setUpChipGroupView(genres: List<MovieGenre>) {
        genres.forEach { item ->
            binding.genresChipGroup.addView(item.toChipComponent(requireContext()))
        }
    }

    private fun onTouchedGenreItem(id: Int, isChecked: Boolean) {}

}
