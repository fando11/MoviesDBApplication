package com.example.bt24.module

import android.content.Context
import com.example.api_service.service.*
import com.example.common.entity.RetrofitClient
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiServiceModule {

    @Provides
    @Singleton
    fun provideRetrofit(context: Context) = RetrofitClient.getClient(context)

    @Provides
    @Singleton
    fun provideGenreService(retrofit: Retrofit): GenreService =
        retrofit.create(GenreService::class.java)

    @Provides
    @Singleton
    fun provideDiscoverMovieService(retrofit: Retrofit): DiscoverMovieService =
        retrofit.create(DiscoverMovieService::class.java)

    @Provides
    @Singleton
    fun provideMovieDetailService(retrofit: Retrofit): MovieDetailService =
        retrofit.create(MovieDetailService::class.java)

    @Provides
    @Singleton
    fun provideMovieDetailTrailerService(retrofit: Retrofit): MovieDetailTrailerService =
        retrofit.create(MovieDetailTrailerService::class.java)

    @Provides
    @Singleton
    fun provideMovieReviewService(retrofit: Retrofit): MovieReviewService =
        retrofit.create(MovieReviewService::class.java)
}