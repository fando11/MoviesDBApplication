package com.example.bt24.module

import com.example.api_service.usecase.DiscoverMoviePagingUseCase
import com.example.api_service.usecase.GetGenreUseCase
import com.example.api_service.usecase.MovieDetailUseCase
import com.example.api_service.usecase.MovieReviewPagingUseCase
import com.example.moviesapplication.view_model.DiscoverMovieViewModel
import com.example.moviesapplication.view_model.GenresMovieViewModel
import com.example.moviesapplication.view_model.MovieDetailViewModel
import dagger.Module
import dagger.Provides
import dagger.android.DaggerApplication

@Module(includes = [UseCaseModule::class])
class ViewModelModule {

    @Provides
    fun provideGenresMovieViewModel(
        application: DaggerApplication,
        getGenreUseCase: GetGenreUseCase
    ) = GenresMovieViewModel(application, getGenreUseCase)

    @Provides
    fun provideDiscoverMovieViewModel(
        application: DaggerApplication,
        discoverMoviePagingUseCase: DiscoverMoviePagingUseCase
    ) = DiscoverMovieViewModel(application, discoverMoviePagingUseCase)

    @Provides
    fun provideMovieDetailViewModel(
        application: DaggerApplication,
        movieDetailUseCase: MovieDetailUseCase,
        movieReviewPagingUseCase: MovieReviewPagingUseCase
    ) = MovieDetailViewModel(application, movieDetailUseCase, movieReviewPagingUseCase)
}