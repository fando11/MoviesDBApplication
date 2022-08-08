package com.example.moviesapplication.activity.genre

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapplication.R
import com.example.moviesapplication.databinding.ActivityGenresMovieListBinding
import com.example.moviesapplication.view_model.GenresMovieViewModel
import javax.inject.Inject

class GenresMovieListActivity : AppCompatActivity() {
    val layoutResourceId: Int = R.layout.activity_genres_movie_list
    lateinit var binding: ActivityGenresMovieListBinding
    val adapter = GenreMovieListAdapter( ::getSelectedGenres)

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory
    val vm : GenresMovieViewModel by viewModels {
        vmFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        observeLiveData()
    }
}