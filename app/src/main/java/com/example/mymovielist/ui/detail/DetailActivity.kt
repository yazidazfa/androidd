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
    private var isIconChanged = false
    private lateinit var favViewModel: FavViewModel
    private lateinit var judul: String
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailActivityBinding.inflate(layoutInflater)
        setContentView(R.layout.detail_activity)

        favViewModel = ViewModelProvider(this).get(FavViewModel::class.java)

        val judul = intent.getStringExtra(EXTRA_JUDUL) ?: ""
        Log.d("DetailActivity", "Received judul: $judul")

        favViewModel.getFavoriteMovieByJudul(judul).observe(this, { favoriteMovie ->
            if (favoriteMovie != null) {
                // FavoriteMovie found, update UI with its details
                Log.d("DetailActivity", "Found favorite movie: $favoriteMovie")
                updateUI(favoriteMovie)
            } else {
                // FavoriteMovie not found, show error message or handle gracefully
                showToast("Favorite movie not found")
                Log.e("DetailActivity", "Favorite movie not found")
            }
        })

        val toolbar: Toolbar = findViewById(toolbar2)
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""

        val fab: FloatingActionButton = findViewById(R.id.fab)

        fab.setOnClickListener {
            toggleFavorite()
        }

        movie = intent.getParcelableExtra(EXTRA_MOVIE) ?: throw IllegalArgumentException("Movie not found")
        Log.d("DetailActivity", "Received movie: $movie")
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
        if (isMovieFavorite(movie)) {
            // Movie is already a favorite
            isIconChanged = true
            updateFabIcon()
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }



    }
    private fun updateUI(favoriteMovie: FavoriteMovie) {
        // Populate UI with FavoriteMovie details
        binding.textViewJudul.text = favoriteMovie.judul
        binding.textViewDesc.text = favoriteMovie.desc
        binding.textViewDirector.text = favoriteMovie.director
        binding.textViewGenre.text = favoriteMovie.genre
        binding.textViewRating.text = favoriteMovie.rating.toString()
        binding.textViewTahun.text = favoriteMovie.tahunRilis.toString()

        Glide.with(this)
            .load(favoriteMovie.poster)
            .placeholder(R.drawable.poster_film) // Placeholder image while loading
            .error(R.drawable.poster_film) // Image to display in case of error
            .into(binding.imageView2)
        // Populate other UI elements similarly
        // ...
    }
    private fun isMovieFavorite(movie: Movie): Boolean {
        val favoriteMovie = favViewModel.getFavoriteMovieByJudul(movie.judul ?: "").value
        return favoriteMovie != null
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun observeMovieDetails(movie: FavoriteMovie) {
        favViewModel.getFavoriteMovieByJudul(judul).observe(this, { movie ->
            // Update UI with the details of the movie
        })
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
    private fun removeFavoriteMovie() {
        movie.judul?.let { judul ->
            favViewModel.removeFavoriteMovieByJudul(judul)
            Toast.makeText(this, "Movie removed from favorites", Toast.LENGTH_SHORT).show()
        }
    }
    private fun updateFabIcon() {
        val fab: FloatingActionButton = findViewById(R.id.fab)
        val iconResource = if (isIconChanged) R.drawable.ic_fav else R.drawable.ic_notfav
        fab.setImageResource(iconResource)
    }

    private fun toggleFavorite() {
        if (!isIconChanged) {
            insertFavoriteMovie()
            isIconChanged = true
            updateFabIcon()

        } else {
            removeFavoriteMovie()
            isIconChanged = false
            updateFabIcon()
            // Handle remove from favorites
        }
    }

    companion object{
        val MOVIE_CHILD = "Film"
        val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_JUDUL = "extra_judul"
        const val EXTRA_FAVORITE_MOVIE = "fav-movie"
    }
}

