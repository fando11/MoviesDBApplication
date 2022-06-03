package com.example.api_service.usecase

import com.example.api_service.pagging.MovieReviewDataSource
import com.example.api_service.service.MovieReviewService

class MovieReviewPagingUseCase(
    val movieReviewService: MovieReviewService
) {
    operator fun invoke(movieId: Int) = MovieReviewDataSource.createPager(
        10, movieReviewService , movieId
    ).flow
}