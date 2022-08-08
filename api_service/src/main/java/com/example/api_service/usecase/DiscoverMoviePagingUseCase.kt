package com.example.api_service.usecase


import com.example.api_service.pagging.DiscoverMovieDataSource
import com.example.api_service.service.DiscoverMovieService

class DiscoverMoviePagingUseCase(
    val discoverMovieService: DiscoverMovieService
) {
    operator fun invoke(genreIds: Array<String>) = DiscoverMovieDataSource.createPager(
        10,discoverMovieService,genreIds
    ).flow
}