package com.example.mymovielist.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mymovielist.R
import com.example.mymovielist.R.id.toolbar2
import com.example.mymovielist.databinding.DetailActivityBinding
import com.example.mymovielist.model.FavoriteMovie
import com.example.mymovielist.model.Movie
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailActivity : AppCompatActivity() {
    @SuppressLint("CutPasteId")

    private lateinit var binding: DetailActivityBinding
    private lateinit var movie: Movie
    private var isIconChanged = true
    private lateinit var favViewModel: FavViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailActivityBinding.inflate(layoutInflater)
        setContentView(R.layout.detail_activity)

        favViewModel = ViewModelProvider(this).get(FavViewModel::class.java)

        val toolbar: Toolbar = findViewById(toolbar2)
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""

        val fab: FloatingActionButton = findViewById(R.id.fab)

        fab.setOnClickListener {
            toggleFavorite()
        }

        movie = intent.getParcelableExtra(EXTRA_MOVIE) ?: throw IllegalArgumentException("Movie not found")

        val imageView = findViewById<ImageView>(R.id.imageView2)

        if (movie != null) {
            val movieJudul = movie.judul
            val movieDesc = movie.desc
            val movieGenre = movie.genre
            val moviePoster = movie.poster
            val movieRating = movie.rating
            val movieRelease = movie.tahunRilis
            val movieDirector = movie.director

            findViewById<TextView>(R.id.textViewJudul).text = movieJudul
            findViewById<TextView>(R.id.textViewDesc).text = movieDesc
            findViewById<TextView>(R.id.textViewGenre).text = movieGenre
            findViewById<TextView>(R.id.textViewDirector).text = movieDirector
            findViewById<TextView>(R.id.textViewRating).text = movieRating.toString()
            findViewById<TextView>(R.id.textViewTahun).text = movieRelease.toString()

            Glide.with(this)
                .load(moviePoster)
                .into(imageView)
        } else {

            Toast.makeText(this, "Movie object is null", Toast.LENGTH_SHORT).show()
            finish()
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

    }

    private fun insertFavoriteMovie() {
        val favoriteMovie = FavoriteMovie(
            movie.id,
            movie.judul,
            movie.poster,
            movie.genre,
            movie.desc,
            movie.director,
            movie.rating,
            movie.tahunRilis
        )
        favViewModel.insertFavoriteMovie(favoriteMovie)
        Toast.makeText(this, "Movie added to favorites", Toast.LENGTH_SHORT).show()
    }
    private fun updateFabIcon() {
        val fab: FloatingActionButton = findViewById(R.id.fab)
        val iconResource = if (isIconChanged) R.drawable.ic_fav else R.drawable.ic_notfav
        fab.setImageResource(iconResource)
    }

    private fun toggleFavorite() {
        if (isIconChanged) {
            insertFavoriteMovie()
        } else {
            // Handle remove from favorites
        }
        isIconChanged = !isIconChanged
        updateFabIcon()
    }
    private fun obtainViewModel(activity: AppCompatActivity): FavViewModel {
        val factory = FavViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(FavViewModel::class.java)
}
    companion object{
        val MOVIE_CHILD = "Film"
        val EXTRA_MOVIE = "extra_movie"
    }
}

