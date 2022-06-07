package com.example.moviesapplication.activity.discover_movie

import android.app.ProgressDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import com.example.moviesapplication.BR
import com.example.moviesapplication.activity.genre.GenresMovieListActivity
import com.example.moviesapplication.activity.genre.observeLiveData
import dagger.android.AndroidInjection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun DiscoverMoviesActivity.initBinding() {
    binding =
        DataBindingUtil.inflate(
            LayoutInflater.from(this), layoutResourceId, null, false)
    binding.lifecycleOwner = this
    setContentView(binding.root)
    AndroidInjection.inject(this)
    binding.setVariable(BR.vm, vm)

}

fun DiscoverMoviesActivity.observeLiveData() = with(vm){
    binding.recycler.adapter = adapter
    genreId.value = intent.getIntExtra("EXTRA_DATA_GENRE_ID",1)
    genreId.observe(this@observeLiveData){
        CoroutineScope(Dispatchers.IO).launch {
            discoverMoviesByGenre(it)
        }
    }

    pagingData.observe(this@observeLiveData){
        adapter.addLoadStateListener {
            if(it.refresh is LoadState.Error) {
                binding.retry.visibility = View.VISIBLE
            }else{
                binding.retry.visibility = View.GONE
            }
        }
        CoroutineScope(Dispatchers.IO).launch {
            adapter.submitData(it)
        }

    }

    binding.retry.setOnClickListener {
        genreId.observe(this@observeLiveData){
            CoroutineScope(Dispatchers.IO).launch {
                discoverMoviesByGenre(it)
            }
        }
    }



}