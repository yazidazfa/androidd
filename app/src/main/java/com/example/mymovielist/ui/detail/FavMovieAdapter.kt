package com.example.mymovielist.ui.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovielist.R
import com.example.mymovielist.databinding.ItemMovieBinding
import com.example.mymovielist.model.FavoriteMovie
import com.bumptech.glide.Glide

class FavoriteMovieAdapter(private val context: Context, public var favoriteMovies: List<FavoriteMovie>?, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<FavoriteMovieAdapter.FavoriteMovieViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(favoriteMovie: FavoriteMovie)
    }

    inner class FavoriteMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMovieBinding.bind(itemView)

        fun bind(favoriteMovie: FavoriteMovie) {
            with(binding) {
                tvJudulFilm.text = favoriteMovie.judul
                tvGenreFilm.text = favoriteMovie.genre
                tvRatingFilm.text = favoriteMovie.rating.toString()
                tvTahunTerbit.text = favoriteMovie.tahunRilis.toString()

                Glide.with(context)
                    .load(favoriteMovie.poster)
                    .placeholder(R.drawable.poster_film)
                    .error(R.drawable.poster_film)
                    .into(imageView)

                itemView.setOnClickListener {
                    listener.onItemClick(favoriteMovie)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return FavoriteMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteMovieViewHolder, position: Int) {
        favoriteMovies?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = favoriteMovies?.size ?: 0

}
