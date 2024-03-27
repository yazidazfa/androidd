package com.example.mymovielist.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.mymovielist.R
import com.example.mymovielist.databinding.FavActivityBinding
import com.example.mymovielist.model.FavoriteMovie
import com.example.mymovielist.model.MemberDao
import com.example.mymovielist.model.MovieRoomDatabase
import com.example.mymovielist.ui.main.MovieAdapter
import com.google.android.material.appbar.MaterialToolbar


class FavActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FavActivityBinding.inflate(layoutInflater)
        setContentView(R.layout.fav_activity)

        val toolbar: MaterialToolbar = findViewById(R.id.toolbar2)
        setSupportActionBar(toolbar)

        toolbar.title = getString(R.string.fav_menu)
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.rvMovie.layoutManager = LinearLayoutManager(this)
        binding.rvMovie.setHasFixedSize(true)

        favViewModel = obtainViewModel(this@FavActivity)

        Toast.makeText(this, "FavActivity Created", Toast.LENGTH_SHORT).show()
    }
    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20
    }


    private var isEdit = false
    private var member: FavoriteMovie? = null

    private lateinit var favViewModel: FavViewModel
    private lateinit var binding: FavActivityBinding

    private fun obtainViewModel(activity: AppCompatActivity): FavViewModel {
        val factory = FavViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(FavViewModel::class.java)
    }

}