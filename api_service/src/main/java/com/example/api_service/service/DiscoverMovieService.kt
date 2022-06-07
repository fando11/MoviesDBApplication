package com.example.api_service.service

import com.example.common.`object`.Constants

import com.example.common.entity.discover_movie.DiscoverMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DiscoverMovieService {
    @GET("discover/movie")
    suspend fun getMovieDiscover(
        @Query("with_genres") genres: String,
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): Response<DiscoverMoviesResponse>
}