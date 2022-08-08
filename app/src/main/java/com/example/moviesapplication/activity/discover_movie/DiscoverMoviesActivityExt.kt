package com.example.moviesapplication.activity.discover_movie

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import com.example.moviesapplication.BR
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

    binding.recycler.adapter = adapter

}

fun DiscoverMoviesActivity.observeLiveData() = with(vm){
    val selectedGenreIds = intent.getStringArrayListExtra("EXTRA_DATA_GENRE_IDS")
    genreIds.value = selectedGenreIds?.toTypedArray()
    genreIds.observe(this@observeLiveData){
        CoroutineScope(Dispatchers.IO).launch {
            discoverMoviesByGenre(it)
        }

        moviePagingData.observe(this@observeLiveData){
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
            genreIds.observe(this@observeLiveData){
                CoroutineScope(Dispatchers.IO).launch {
                    discoverMoviesByGenre(it)
                }
            }
        }
    }





}