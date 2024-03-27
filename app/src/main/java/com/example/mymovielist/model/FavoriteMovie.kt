package com.example.mymovielist.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class FavoriteMovie(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "desc")
    var judul: String? = null,

    @ColumnInfo(name = "director")
    var poster: String? = null,

    @ColumnInfo(name = "genre")
    var genre: String? = null,

    @ColumnInfo(name = "judul")
    var desc: String? = null,

    @ColumnInfo(name = "poster")
    var director: String? = null,

    @ColumnInfo(name = "rating")
    var rating: Float? = null,

    @ColumnInfo(name = "tahunRilis")
    var tahunRilis: Int? = null,
) : Parcelable
