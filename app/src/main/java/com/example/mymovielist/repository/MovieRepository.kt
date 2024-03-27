package com.example.mymovielist.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymovielist.model.Movie
import com.example.mymovielist.model.MemberDao
import com.example.mymovielist.model.FavoriteMovie
import com.example.mymovielist.model.MovieRoomDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
class MovieRepository (application: Application) {
    private val memberDao: MemberDao = MovieRoomDatabase.getDatabase(application).memberDao()
    private val movieRef: DatabaseReference = FirebaseDatabase.getInstance().reference.child(MOVIE_CHILD)

    private val _moviesLiveData = MutableLiveData<List<Movie>>()
    val moviesLiveData: LiveData<List<Movie>> get() = _moviesLiveData
    fun getAllMoviesFromFirebase() {
        movieRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val movies = mutableListOf<Movie>()
                for (movieSnap in snapshot.children) {
                    movieSnap.getValue(Movie::class.java)?.let { movie ->
                        movie.judul = movieSnap.key.toString()
                        movies.add(movie)
                    }
                }
                _moviesLiveData.postValue(movies)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

    fun insertFavoriteMovie(favoriteMovie: FavoriteMovie) {
        MovieRoomDatabase.databaseWriteExecutor.execute {
            memberDao.insert(favoriteMovie)
        }
    }

    companion object {
        const val MOVIE_CHILD = "Film"
    }


}