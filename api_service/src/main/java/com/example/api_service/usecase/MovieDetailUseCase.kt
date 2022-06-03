package com.example.api_service.usecase

import com.example.api_service.service.MovieDetailService
import com.example.api_service.service.MovieDetailTrailerService
import com.example.common.entity.movie.movie_detail.MovieDetailResponse
import com.example.common.entity.movie.movie_trailer.MovieTrailerResponse
import com.example.common.entity.respose.AppResponse2
import kotlinx.coroutines.flow.flow

class MovieDetailUseCase(
    val movieDetailService: MovieDetailService,
    val movieDetailTrailerService: MovieDetailTrailerService
) {
    operator fun invoke(movieId: Int) = flow<AppResponse2<MovieDetailResponse, MovieTrailerResponse?>> {
        emit(AppResponse2.loading())
        val data = movieDetailService.getDetails(movieId)
        if (data.isSuccessful){
            data.body()?.let {
                val dataDetail = it
                val dataTrailer = movieDetailTrailerService.getVideos(movieId)
                dataTrailer.body()?.let { trailer ->
                    emit(AppResponse2.success(dataDetail,trailer))
                }?:run{
                    emit(AppResponse2.success(dataDetail,null))
                }
            }?: run{
                emit(AppResponse2.errorBackend(data.code(),data.errorBody()))
            }
        }else{
            emit(AppResponse2.errorBackend(data.code(), data.errorBody()))
        }

    }
}