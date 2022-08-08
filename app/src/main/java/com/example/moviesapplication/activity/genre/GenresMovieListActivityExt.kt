package com.example.moviesapplication.activity.genre

import android.app.ProgressDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.common.entity.respose.AppResponse
import com.example.moviesapplication.BR
import com.example.moviesapplication.activity.discover_movie.DiscoverMoviesActivity
import dagger.android.AndroidInjection


fun GenresMovieListActivity.initBinding() {
    binding =
        DataBindingUtil.inflate(
            LayoutInflater.from(this), layoutResourceId, null, false)
    binding.lifecycleOwner = this
    setContentView(binding.root)
    AndroidInjection.inject(this)
    binding.setVariable(BR.vm, vm)

    binding.recycler.adapter = adapter

    binding.fabNext.setOnClickListener {
        val selectedGenres = getSelectedGenres()
        val selectedGenreIds = ArrayList<String>()
        selectedGenreIds.addAll(selectedGenres.map { it.id.toString() })
        val intent = Intent(this, DiscoverMoviesActivity::class.java)
        intent.putStringArrayListExtra("EXTRA_DATA_GENRE_IDS", selectedGenreIds)
        startActivity(intent)
    }

    binding.retry.setOnClickListener {
        vm.getGenresMovies()
    }
}

fun GenresMovieListActivity.getSelectedGenres() = vm.selectedGenres

fun GenresMovieListActivity.observeLiveData() = with(vm){

    var dialog: ProgressDialog? = null
    dataGenre.observe(this@observeLiveData){
        when (it.state) {
            AppResponse.ERROR -> {
                dialog?.dismiss()
                Toast.makeText(this@observeLiveData, "error ${it.code.toString()}", Toast.LENGTH_SHORT).show()
                binding.retry.visibility = View.VISIBLE
            }
            AppResponse.SUCCESS -> {
                dialog?.dismiss()
                binding.retry.visibility = View.GONE
                adapter.submitData(it.data.orEmpty())


            }
            AppResponse.LOADING -> {
                binding.retry.visibility = View.GONE
                dialog = ProgressDialog(this@observeLiveData).apply {
                    setCancelable(false)
                    setProgressStyle(ProgressDialog.STYLE_SPINNER)
                    setMessage("Memroses...")
                    show()
                }
            }
        }


    }


}




