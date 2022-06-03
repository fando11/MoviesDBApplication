package com.example.moviesapplication.activity.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapplication.R
import com.example.moviesapplication.databinding.ActivityMovieDetailBinding
import com.example.moviesapplication.view_model.MovieDetailViewModel
import javax.inject.Inject

class MovieDetailActivity : AppCompatActivity() {
    val layoutResourceId: Int = R.layout.activity_movie_detail
    lateinit var binding: ActivityMovieDetailBinding

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory
    val vm : MovieDetailViewModel by viewModels {
        vmFactory
    }

    val adapter =  MovieReviewPagingAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        observeLiveData()
    }
}