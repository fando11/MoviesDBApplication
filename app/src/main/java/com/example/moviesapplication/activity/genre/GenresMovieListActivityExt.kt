package com.example.moviesapplication.activity.genre

import android.app.ProgressDialog
import android.view.LayoutInflater
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.common.entity.respose.AppResponse
import com.example.moviesapplication.BR
import dagger.android.AndroidInjection


fun GenresMovieListActivity.initBinding() {
    binding =
        DataBindingUtil.inflate(
            LayoutInflater.from(this), layoutResourceId, null, false)
    binding.lifecycleOwner = this
    setContentView(binding.root)
    AndroidInjection.inject(this)
    binding.setVariable(BR.vm, vm)

}

fun GenresMovieListActivity.observeLiveData() = with(vm){
    binding.recycler.adapter = adapter

    var dialog: ProgressDialog? = null
    dataGenre.observe(this@observeLiveData){
        when (it.state) {
            AppResponse.ERROR -> {
                adapter.submitData(emptyList())
                dialog?.dismiss()
                Toast.makeText(this@observeLiveData, "error ${it.code.toString()}", Toast.LENGTH_SHORT).show()
            }
            AppResponse.SUCCESS -> {
                adapter.submitData(it.data.orEmpty())
                dialog?.dismiss()
                Toast.makeText(this@observeLiveData, "List Genre Ada", Toast.LENGTH_SHORT).show()
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


}




