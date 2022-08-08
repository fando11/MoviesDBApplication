package com.example.api_service.usecase

import com.example.api_service.service.MovieDetailService
import com.example.api_service.service.MovieDetailTrailerService
import com.example.common.entity.movie.movie_detail.MovieDetailResponse
import com.example.common.entity.movie.movie_trailer.MovieTrailerResponse
import com.example.common.entity.respose.AppResponse
import kotlinx.coroutines.flow.flow

class MovieDetailUseCase(
    val movieDetailService: MovieDetailService,
    val movieDetailTrailerService: MovieDetailTrailerService
) {
    operator fun invoke(movieId: Int) = flow<AppResponse<Pair<MovieDetailResponse, MovieTrailerResponse?>>> {
        emit(AppResponse.loading())
        val data = movieDetailService.getDetails(movieId)
        if (data.isSuccessful){
            data.body()?.let {
                val dataDetail = it
                val dataTrailer = movieDetailTrailerService.getVideos(movieId)
                dataTrailer.body()?.let { trailer ->
                    emit(AppResponse.success(dataDetail to trailer))
                }?:run{
                    emit(AppResponse.success(dataDetail to null))
                }
            }?: run{
                emit(AppResponse.errorBackend(data.code(),data.errorBody()))
            }
        }else{
            emit(AppResponse.errorBackend(data.code(), data.errorBody()))
        }

    }
}