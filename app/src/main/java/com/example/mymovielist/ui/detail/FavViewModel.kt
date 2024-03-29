package com.example.mymovielist.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovielist.model.FavoriteMovie
import com.example.mymovielist.model.MovieRoomDatabase
import com.example.mymovielist.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavViewModel (application: Application): AndroidViewModel(application) {
    private val repository: MovieRepository = MovieRepository(application)
    fun insertFavoriteMovie(favoriteMovie: FavoriteMovie) {
        repository.insertFavoriteMovie(favoriteMovie)
    }
    fun removeFavoriteMovieByJudul(judul: String) {
        MovieRoomDatabase.databaseWriteExecutor.execute {
            repository.removeFavoriteMovieByJudul(judul)
        }
    }
    fun getFavoriteMovieByJudul(judul: String): LiveData<FavoriteMovie?> {
        return repository.getFavoriteMovieByJudul(judul)
    }
    fun getAllFavoriteMovies(): LiveData<List<FavoriteMovie>> {
        return repository.getAllFavoriteMovies()
    }
    fun isMovieFavorited(movieId: Int): LiveData<Boolean> {
        return repository.isMovieFavorited(movieId)
    }
}

