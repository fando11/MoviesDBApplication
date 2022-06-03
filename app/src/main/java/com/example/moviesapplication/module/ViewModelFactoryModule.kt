package com.example.bt24.module

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.common.entity.annotation.ViewModelKey
import com.example.common.entity.ui.ViewModelProviderFactory
import com.example.moviesapplication.activity.discover_movie.DiscoverMoviesActivity
import com.example.moviesapplication.activity.genre.GenresMovieListActivity
import com.example.moviesapplication.activity.movie.MovieDetailActivity
import com.example.moviesapplication.view_model.DiscoverMovieViewModel
import com.example.moviesapplication.view_model.GenresMovieViewModel
import com.example.moviesapplication.view_model.MovieDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.DaggerApplication
import dagger.multibindings.IntoMap


@Module(includes = [ViewModelModule::class])
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun provideContext(application: DaggerApplication) : Context

    @Binds
    abstract fun provideVmFactory(vmFactory: ViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(GenresMovieViewModel::class)
    abstract fun bindGenresMovieViewModel(genresMovieViewModel: GenresMovieViewModel) : ViewModel

    @ContributesAndroidInjector
    abstract fun contributeGenreActivity(): GenresMovieListActivity

    @Binds
    @IntoMap
    @ViewModelKey(DiscoverMovieViewModel::class)
    abstract fun bindDiscoverMovieViewModel(discoverMovieViewModel: DiscoverMovieViewModel) : ViewModel

    @ContributesAndroidInjector
    abstract fun contributeDiscoverMoviesActivity(): DiscoverMoviesActivity

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    abstract fun bindMovieDetailViewModel(movieDetailViewModel: MovieDetailViewModel) : ViewModel

    @ContributesAndroidInjector
    abstract fun contributeMovieDetailActivity(): MovieDetailActivity

}