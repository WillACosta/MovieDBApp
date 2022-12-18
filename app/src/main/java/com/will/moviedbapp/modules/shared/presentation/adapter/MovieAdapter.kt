package com.will.moviedbapp.modules.shared.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.will.moviedbapp.core.constants.AppConstants
import com.will.moviedbapp.core.utils.extensions.load
import com.will.moviedbapp.databinding.MovieListTileBinding
import com.will.moviedbapp.modules.home.domain.model.Movie

class MovieAdapter(
    private val onItemClicked: (Movie) -> Unit,
    private val movies: MutableList<Movie> = mutableListOf()
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val item = MovieListTileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(item)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position], onItemClicked)
    }

    override fun getItemCount(): Int = movies.count()

    inner class MovieViewHolder(
        private val bind: MovieListTileBinding
    ) : RecyclerView.ViewHolder(bind.root) {
        fun bind(movie: Movie, onItemClicked: (Movie) -> Unit) {
            bind.moviePoster.load(AppConstants.Network.BASE_POSTER_URL + movie.posterPath)
            bind.moviePoster.setOnClickListener {
                onItemClicked(movie)
            }
        }
    }

    fun submitList(list: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(list)

        notifyDataSetChanged()
    }
}
