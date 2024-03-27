package com.example.mymovielist.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.mymovielist.model.Movie
import com.example.mymovielist.model.MemberDao
import com.example.mymovielist.model.FavoriteMovie
import com.example.mymovielist.model.MovieRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MovieRepository (application: Application) {
    private val mMemberDao: MemberDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = MovieRoomDatabase.getDatabase(application)
        mMemberDao = db.memberDao()
    }

    fun getAllMember(): LiveData<List<FavoriteMovie>> = mMemberDao.getAllMember()

    fun insert(member: FavoriteMovie) {
        executorService.execute { mMemberDao.insert(member) }
    }

    fun delete(member: FavoriteMovie) {
        executorService.execute { mMemberDao.delete(member) }
    }


}