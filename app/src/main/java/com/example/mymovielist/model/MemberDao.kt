package com.example.mymovielist.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface MemberDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(memberModel: FavoriteMovie)

    @Delete
    fun delete(memberModel: FavoriteMovie)

    @Query("SELECT * FROM FavoriteMovie WHERE judul = :judul")
    fun getFavoriteMovieByjudul(judul: String): LiveData<FavoriteMovie>

    @Query("SELECT * FROM FavoriteMovie")
    fun getAllMember(): LiveData<List<FavoriteMovie>>
    }
