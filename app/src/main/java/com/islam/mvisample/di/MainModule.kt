package com.islam.mvisample.di

import com.islam.mvisample.data.remote.data_source.CarImagesRemoteDataSource
import com.islam.mvisample.data.remote.data_source.CarImagesRemoteDataSourceImpl
import com.islam.mvisample.data.repositories.CarImagesRepositoryImpl
import com.islam.mvisample.domain.repositories.CarImagesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class MainModule {

    @Binds
    abstract fun bindGetCarImagesRepository(repository: CarImagesRepositoryImpl): CarImagesRepository

    @Binds
    abstract fun bindGetCarImagesRemoteDataSource(dataSource: CarImagesRemoteDataSourceImpl): CarImagesRemoteDataSource

}