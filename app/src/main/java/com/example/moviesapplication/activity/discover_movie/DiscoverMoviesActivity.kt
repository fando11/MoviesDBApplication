package com.example.moviesapplication.activity.discover_movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapplication.R
import com.example.moviesapplication.databinding.ActivityDiscoverMoviesBinding
import com.example.moviesapplication.view_model.DiscoverMovieViewModel
import javax.inject.Inject

class DiscoverMoviesActivity : AppCompatActivity() {
    val layoutResourceId: Int = R.layout.activity_discover_movies
    lateinit var binding: ActivityDiscoverMoviesBinding
    val adapter = DiscoverMoviesAdapter(this)

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory
    val vm : DiscoverMovieViewModel by viewModels {
        vmFactory
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        observeLiveData()
    }
}