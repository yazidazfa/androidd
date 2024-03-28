package com.example.mymovielist.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mymovielist.model.FavoriteMovie
import com.example.mymovielist.model.MovieRoomDatabase
import com.example.mymovielist.repository.MovieRepository

class FavViewModel (application: Application): AndroidViewModel(application) {
    private val repository: MovieRepository = MovieRepository(application)
    val favoriteMovies: LiveData<List<FavoriteMovie>> = repository.getAllFavoriteMovies()

    fun getAllMoviesFromFirebase() = repository.moviesLiveData
    fun fetchMoviesFromFirebase() {
        repository.getAllMoviesFromFirebase()
    }

    fun insertFavoriteMovie(favoriteMovie: FavoriteMovie) {
        repository.insertFavoriteMovie(favoriteMovie)
    }
    fun removeFavoriteMovieByJudul(judul: String) {
        MovieRoomDatabase.databaseWriteExecutor.execute {
            repository.removeFavoriteMovieByJudul(judul)
        }
    }
    fun getFavoriteMovieByJudul(judul: String): LiveData<FavoriteMovie> {
        return repository.getFavoriteMovieByJudul(judul)
    }
    fun getAllFavoriteMovies(): LiveData<List<FavoriteMovie>> {
        return repository.getAllFavoriteMovies()
    }
}

