package com.example.moviesapplication.activity.movie

import android.app.ProgressDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.common.entity.respose.AppResponse
import com.example.moviesapplication.BR
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.DefaultPlayerUiController
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


    binding.recycler.adapter = adapter

}

fun MovieDetailActivity.observeLiveData() = with(vm){
    movieId.value = intent.getIntExtra("EXTRA_DATA_MOVIE_ID",1)
    movieId.observe(this@observeLiveData){
        CoroutineScope(Dispatchers.IO).launch {
            getMovieDetail(it)
        }

        var dialog: ProgressDialog? = null
        state.observe(this@observeLiveData){
            when(it){
                AppResponse.ERROR -> {
                    dialog?.dismiss()
                    Toast.makeText(this@observeLiveData, "Data Detail Tidak Ada", Toast.LENGTH_SHORT).show()
                    binding.retry.visibility = View.VISIBLE
                }
                AppResponse.SUCCESS -> {
                    binding.retry.visibility = View.GONE
                    dialog?.dismiss()
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

        dataReview.observe(this@observeLiveData){
            CoroutineScope(Dispatchers.IO).launch {
                adapter.submitData(it)
            }
        }


        videoId.observe(this@observeLiveData){ videoId ->
            val listener = object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    videoId.let { youTubePlayer.cueVideo(it, 0f) }

                    val defaultPlayerUiController =
                        DefaultPlayerUiController(binding.videoTrailer, youTubePlayer)
                    binding.videoTrailer.setCustomPlayerUi(defaultPlayerUiController.rootView)
                }
            }

            val option = IFramePlayerOptions.Builder().controls(0).build()
            binding.videoTrailer.initialize(listener, option)
        }

        binding.retry.setOnClickListener {
            movieId.observe(this@observeLiveData){
                CoroutineScope(Dispatchers.IO).launch {
                    getMovieDetail(it)
                }
            }
        }
    }







}