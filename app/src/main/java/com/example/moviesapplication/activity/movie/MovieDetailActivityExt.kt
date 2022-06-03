package com.example.moviesapplication.activity.movie

import android.app.ProgressDialog
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.common.entity.respose.AppResponse
import com.example.moviesapplication.BR
import com.example.moviesapplication.R
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragmentX
import dagger.android.AndroidInjection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun MovieDetailActivity.initBinding() {
    binding =
        DataBindingUtil.inflate(
            LayoutInflater.from(this), layoutResourceId, null, false)
    binding.lifecycleOwner = this
    setContentView(binding.root)
    AndroidInjection.inject(this)
    binding.setVariable(BR.vm, vm)

}

fun MovieDetailActivity.observeLiveData() = with(vm){
    movieId.value = intent.getIntExtra("EXTRA_DATA_MOVIE_ID",1)
    movieId.observe(this@observeLiveData){
        CoroutineScope(Dispatchers.IO).launch {
            getMovieDetail(it)
        }
    }

    var dialog: ProgressDialog? = null
    state.observe(this@observeLiveData){
        when(it){
            AppResponse.ERROR -> {
                dialog?.dismiss()
                Toast.makeText(this@observeLiveData, "Data Detail Tidak Ada", Toast.LENGTH_SHORT).show()
            }
            AppResponse.SUCCESS -> {
                dialog?.dismiss()
                Toast.makeText(this@observeLiveData, "Data Detail Ada", Toast.LENGTH_SHORT).show()
            }
            AppResponse.LOADING -> {
                dialog = ProgressDialog(this@observeLiveData).apply {
                    setCancelable(false)
                    setProgressStyle(ProgressDialog.STYLE_SPINNER)
                    setMessage("Memroses...")
                    show()
                }
            }
        }
    }
    binding.recycler.adapter = adapter
    dataReview.observe(this@observeLiveData){
        CoroutineScope(Dispatchers.IO).launch {
            adapter.submitData(it)
        }
    }

    videoId.observe(this@observeLiveData){
        val youtubeFragment = YouTubePlayerSupportFragmentX.newInstance()
        with(supportFragmentManager){
            beginTransaction().apply {
                add(R.id.video_trailer, youtubeFragment)
                commit()
            }
            youtubeFragment.initialize(
                "AIzaSyAOlyB8Oc9j3wtckL5EFd0fbrPYPWsvx6c",
                object : YouTubePlayerSupportFragmentX.OnInitializedListener(){
                    override fun onInitializationSuccess(
                        p0: YouTubePlayer.Provider?,
                        p1: YouTubePlayer?,
                        p2: Boolean
                    ) {
                        p1?.cueVideo(it)
                    }

                    override fun onInitializationFailure(
                        p0: YouTubePlayer.Provider?,
                        p1: YouTubeInitializationResult?
                    ) {
                        Log.e("YoutubePlayer","error ${p1?.name}")
                    }

                }
            )
        }
    }



}