package com.example.mymovielist.ui.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.mymovielist.model.FavoriteMovie

import com.example.mymovielist.repository.MovieRepository

class FavViewModel (application: Application): ViewModel() {
    private val mMovieRepository: MovieRepository = MovieRepository(application)

    fun insert(member: FavoriteMovie) {
        mMovieRepository.insert(member)
    }

    fun delete(member: FavoriteMovie){
        mMovieRepository.delete(member)
    }
}

