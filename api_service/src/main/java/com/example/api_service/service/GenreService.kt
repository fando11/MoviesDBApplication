package com.example.api_service.service

import com.example.common.entity.Constants

import com.example.common.entity.genre.GenreResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreService {
    @GET("genre/movie/list")
    suspend fun getGenresMovies(
        @Query("api_key") apiKey: String = Constants.API_KEY
    ) : Response<GenreResponse>
}