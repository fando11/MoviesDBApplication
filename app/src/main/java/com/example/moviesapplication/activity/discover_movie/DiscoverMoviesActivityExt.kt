package com.example.moviesapplication.activity.discover_movie

import android.app.ProgressDialog
import android.view.LayoutInflater
import android.widget.Toast
import androidx.databinding.DataBindingUtil
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

}

fun DiscoverMoviesActivity.observeLiveData() = with(vm){
    binding.recycler.adapter = adapter
    genreId.value = intent.getIntExtra("EXTRA_DATA_GENRE_ID",1)
    genreId.observe(this@observeLiveData){
        CoroutineScope(Dispatchers.IO).launch {
            discoverMoviesByGenre(it)
        }
    }
    var dialog: ProgressDialog? = null
    pagingData.observe(this@observeLiveData){
        CoroutineScope(Dispatchers.IO).launch {
            adapter.submitData(it)
        }.let {
            if (it.isActive){
                dialog?.dismiss()
                Toast.makeText(this@observeLiveData, "Data List Movie ada", Toast.LENGTH_SHORT).show()
            }else{
                dialog?.dismiss()
                Toast.makeText(this@observeLiveData, "Data List Movie Tidak ada", Toast.LENGTH_SHORT).show()
            }
        }

    }



}