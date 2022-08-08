package com.example.moviesapplication.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.api_service.usecase.MovieDetailUseCase
import com.example.api_service.usecase.MovieReviewPagingUseCase
import com.example.common.entity.movie.movie_detail.MovieDetailResponse
import com.example.common.entity.movie.movie_review.Result as ReviewResult
import kotlinx.coroutines.launch

class MovieDetailViewModel (
    application: Application,
    val movieDetailUseCase: MovieDetailUseCase,
    val movieReviewPagingUseCase: MovieReviewPagingUseCase
) : AndroidViewModel(application) {

    val dataReview = MutableLiveData<PagingData<ReviewResult>>()
    val dataMovieDetail = MutableLiveData<MovieDetailResponse?>()
    val movieId = MutableLiveData<Int>()
    val videoId = MutableLiveData<String>()
    val state = MutableLiveData<Int>()


    fun getMovieDetail(movieId:Int){
        viewModelScope.launch {
            movieDetailUseCase.invoke(movieId).collect{
                dataMovieDetail.postValue(it.data?.first)
                videoId.postValue(it.data?.second?.results?.last()?.key?.ifEmpty { "ZWcRmoLqhkc" })
                state.postValue(it.state)
            }
            movieReviewPagingUseCase.invoke(movieId).cachedIn(viewModelScope).collect{
                dataReview.postValue(it)
            }
        }
    }
}