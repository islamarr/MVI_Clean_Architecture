package com.move.islam.di

import com.move.islam.data.remote.repositories.GetCarImagesRepositoryImpl
import com.move.islam.domain.repositories.GetCarImagesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class MainModule {

    @Binds
    abstract fun provideRepo(repository: GetCarImagesRepositoryImpl): GetCarImagesRepository

}