package com.will.moviedbapp.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.will.moviedbapp.core.constants.AppConstants
import com.will.moviedbapp.core.utils.extensions.load
import com.will.moviedbapp.databinding.MovieListTileBinding
import com.will.moviedbapp.domain.model.Movie

class MovieAdapter(
    private val movies: List<Movie>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val item = MovieListTileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(item)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.count()

    inner class MovieViewHolder(
        private val bind: MovieListTileBinding
    ) : RecyclerView.ViewHolder(bind.root) {
        fun bind(movie: Movie) {
            bind.moviePoster.load(AppConstants.NETWORK.BASE_POSTER_URL + movie.posterPath)
        }
    }
}
