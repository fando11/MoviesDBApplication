package com.example.moviesapplication.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope

import com.example.api_service.usecase.GetGenreUseCase
import com.example.common.entity.genre.Genre
import com.example.common.entity.respose.AppResponse
import kotlinx.coroutines.launch

class GenresMovieViewModel(
    application: Application,
    val getGenreUseCase: GetGenreUseCase
) : AndroidViewModel(application) {


    val dataGenre = MutableLiveData<AppResponse<List<Genre>>>()
    val selectedGenres = ArrayList<Genre>()

    init{
        getGenresMovies()
    }

    fun getGenresMovies(){
        viewModelScope.launch {
            getGenreUseCase.invoke().collect{
                dataGenre.postValue(it)
            }
        }

    }
}