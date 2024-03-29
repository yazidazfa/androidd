package com.example.mymovielist.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    var id: Int = 0,
    var desc: String? = null,
    var director: String? = null,
    var genre: String? = null,
    var judul: String? = null,
    var poster: String? = null,
    var rating: Float? = null,
    var tahunRilis: Int? = null,

) : Parcelable
