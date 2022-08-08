package com.example.moviesapplication.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.api_service.usecase.DiscoverMoviePagingUseCase
import kotlinx.coroutines.launch
import com.example.common.entity.discover_movie.Result as MoviesResult

class DiscoverMovieViewModel(
    application: Application,
    val discoverMoviePagingUseCase: DiscoverMoviePagingUseCase
) : AndroidViewModel(application) {

    val moviePagingData = MutableLiveData<PagingData<MoviesResult>>()
    val genreIds = MutableLiveData<Array<String>>()


    fun discoverMoviesByGenre(genreIds:Array<String>) {
        viewModelScope.launch {
            discoverMoviePagingUseCase.invoke(genreIds).collect {
                moviePagingData.postValue(it)
            }
        }
    }
}