package com.will.moviedbapp.modules.movie.presentation.genres

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.will.moviedbapp.databinding.GenreListItemBinding
import com.will.moviedbapp.modules.movie.domain.entity.MovieGenre

class GenreAdapter(
    private val onTouchedItem: (Int, Boolean) -> Unit,
    private val genres: MutableList<MovieGenre> = mutableListOf()
) : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val item = GenreListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreViewHolder(item)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, index: Int) {
        // adicionar chip group antes
        holder.bind(genres[index], onTouchedItem)
    }

    override fun getItemCount(): Int = genres.count()

    inner class GenreViewHolder(
        private val binding: GenreListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(genre: MovieGenre, onTouchedItem: (Int, Boolean) -> Unit) {
            binding.genreChip.text = genre.name
            binding.genreChip.setOnCheckedChangeListener { _, isChecked ->
                onTouchedItem(
                    genre.id,
                    isChecked
                )
            }
        }
    }

    fun submitList(list: List<MovieGenre>) {
        this.genres.clear()
        this.genres.addAll(list)

        notifyDataSetChanged()
    }
}
