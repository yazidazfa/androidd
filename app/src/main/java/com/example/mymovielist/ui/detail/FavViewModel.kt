package com.example.mymovielist.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.mymovielist.model.FavoriteMovie
import com.example.mymovielist.repository.MovieRepository

class FavViewModel (application: Application): AndroidViewModel(application) {
    private val repository: MovieRepository = MovieRepository(application)

    fun getAllMoviesFromFirebase() = repository.moviesLiveData
    fun fetchMoviesFromFirebase() {
        repository.getAllMoviesFromFirebase()
    }

    fun insertFavoriteMovie(favoriteMovie: FavoriteMovie) {
        repository.insertFavoriteMovie(favoriteMovie)
    }
}

