package com.example.bt24.module

import com.example.moviesapplication.MoviesApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ViewModelFactoryModule::class])
abstract class ApplicationComponent : AndroidInjector<MoviesApplication> {
    @Component.Builder
    abstract class Builder {

        abstract fun application(apiModule: ApiServiceModule) : Builder
        @BindsInstance
        abstract fun app(application: DaggerApplication): Builder
        abstract fun build(): ApplicationComponent
    }
}