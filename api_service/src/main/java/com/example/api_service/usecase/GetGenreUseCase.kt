package com.example.api_service.usecase

import com.example.api_service.service.GenreService
import com.example.common.entity.genre.Genre
import com.example.common.entity.respose.AppResponse
import kotlinx.coroutines.flow.flow

class GetGenreUseCase(
    val genreService: GenreService
) {
    operator fun invoke() = flow<AppResponse<List<Genre>>> {
        emit(AppResponse.loading())
        val data = genreService.getGenresMovies()
        if(data.isSuccessful){
            data.body()?.let {
                emit(AppResponse.success(it.genres))
            }?: run {
                emit(AppResponse.errorBackend(data.code(),data.errorBody()))
            }
        }else{
            emit(AppResponse.errorBackend(data.code(), data.errorBody()))
        }
    }


}