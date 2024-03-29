package com.example.mymovielist.ui.detail

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.mymovielist.R
import com.example.mymovielist.databinding.FavActivityBinding
import com.example.mymovielist.model.FavoriteMovie
import com.example.mymovielist.model.MemberDao
import com.example.mymovielist.model.Movie
import com.example.mymovielist.model.MovieRoomDatabase
import com.example.mymovielist.ui.main.MovieAdapter
import com.google.android.material.appbar.MaterialToolbar


class FavActivity: AppCompatActivity(), FavoriteMovieAdapter.OnItemClickListener {

    private lateinit var favViewModel: FavViewModel
    private lateinit var binding: FavActivityBinding
    private lateinit var favoriteMovieAdapter: FavoriteMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FavActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: MaterialToolbar = findViewById(R.id.toolbar2)
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""

        toolbar.setNavigationIcon(R.drawable.ic_back)
        binding.toolbar2.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.rvMovie.layoutManager = LinearLayoutManager(this)
        favoriteMovieAdapter = FavoriteMovieAdapter(this, emptyList(), this)
        binding.rvMovie.adapter = favoriteMovieAdapter

        favViewModel = obtainViewModel(this@FavActivity)

        observeFavoriteMovies()
    }

    private fun observeFavoriteMovies() {
        favViewModel.getAllFavoriteMovies().observe(this) { favoriteMovies ->
            favoriteMovieAdapter.updateFavoriteMovies(favoriteMovies)
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): FavViewModel {
        val factory = FavViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(FavViewModel::class.java)
    }

    override fun onItemClick(favoriteMovie: FavoriteMovie) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_FAVORITE_MOVIE, favoriteMovie)
        }
        startActivity(intent)
    }
    companion object {
        const val EXTRA_JUDUL = "extra_judul"
        val EXTRA_FAVVORITE_MOVIE = "fav-movie"
    }

}