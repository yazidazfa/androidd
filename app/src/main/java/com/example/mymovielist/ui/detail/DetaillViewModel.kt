package com.example.mymovielist.ui.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mymovielist.model.FavoriteMovie
import com.example.mymovielist.repository.MovieRepository

class DetailViewModel(application: Application): ViewModel() {
    private val mMovieRepository: MovieRepository = MovieRepository(application)



}