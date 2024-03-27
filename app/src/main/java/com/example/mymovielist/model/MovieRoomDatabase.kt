package com.example.mymovielist.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavoriteMovie::class], version = 1)
abstract class MovieRoomDatabase : RoomDatabase() {
    abstract fun memberDao(): MemberDao

    companion object {
        @Volatile
        private var INSTANCE: MovieRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): MovieRoomDatabase {
            if (INSTANCE == null) {
                synchronized(MovieRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MovieRoomDatabase::class.java,
                        "movie_database"
                    ).build()
                }
            }
            return INSTANCE as MovieRoomDatabase
        }
    }
}


