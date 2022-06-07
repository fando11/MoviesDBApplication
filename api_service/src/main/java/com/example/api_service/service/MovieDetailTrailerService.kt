package com.example.api_service.service

import com.example.common.`object`.Constants
import com.example.common.entity.movie.movie_trailer.MovieTrailerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailTrailerService {
    @GET("movie/{movie_id}/videos?")
    suspend fun getVideos(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): Response<MovieTrailerResponse>
}