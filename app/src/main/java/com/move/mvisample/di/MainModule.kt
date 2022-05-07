package com.move.mvisample.di

import com.move.mvisample.data.remote.repositories.GetCarImagesRepositoryImpl
import com.move.mvisample.domain.repositories.GetCarImagesRepository
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