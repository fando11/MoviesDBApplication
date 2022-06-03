package com.example.bt24.module

import com.example.api_service.service.*
import com.example.api_service.usecase.DiscoverMoviePagingUseCase
import com.example.api_service.usecase.GetGenreUseCase
import com.example.api_service.usecase.MovieDetailUseCase
import com.example.api_service.usecase.MovieReviewPagingUseCase
import dagger.Module
import dagger.Provides

@Module(includes = [ApiServiceModule::class])
class UseCaseModule {

    @Provides
    fun provideGetAllGenreUseCase(genreService: GenreService) = GetGenreUseCase(genreService)

    @Provides
    fun provideDiscoverMovieUseCase(discoverMovieService: DiscoverMovieService) = DiscoverMoviePagingUseCase(discoverMovieService)

    @Provides
    fun provideMovieDetailUseCase(movieDetailService: MovieDetailService,movieDetailTrailerService: MovieDetailTrailerService) = MovieDetailUseCase(movieDetailService,movieDetailTrailerService)

    @Provides
    fun provideMovieReviewPagingUseCase(movieReviewService: MovieReviewService) = MovieReviewPagingUseCase(movieReviewService)

}