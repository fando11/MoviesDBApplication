package com.example.api_service.service

import com.example.common.entity.Constants
import com.example.common.entity.movie.movie_review.MovieReviewResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieReviewService {

    @GET("movie/{movie_id}/reviews?")
    suspend fun getReviews(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): Response<MovieReviewResponse>
}