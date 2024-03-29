package com.example.mymovielist.helper

import com.example.mymovielist.model.FavoriteMovie
import com.example.mymovielist.model.Movie

fun FavoriteMovie.toMovie(): Movie {
    return Movie(
        id = this.id,
        judul = this.judul,
        poster = this.poster,
        genre = this.genre,
        desc = this.desc,
        director = this.director,
        rating = this.rating,
        tahunRilis = this.tahunRilis
    )
}