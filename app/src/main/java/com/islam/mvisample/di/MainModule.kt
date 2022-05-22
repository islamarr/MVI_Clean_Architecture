package com.islam.mvisample.di

import com.islam.mvisample.data.remote.data_source.GetCarImagesRemoteDataSource
import com.islam.mvisample.data.remote.data_source.GetCarImagesRemoteDataSourceImpl
import com.islam.mvisample.data.repositories.GetCarImagesRepositoryImpl
import com.islam.mvisample.domain.repositories.GetCarImagesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class MainModule {

    @Binds
    abstract fun bindGetCarImagesRepository(repository: GetCarImagesRepositoryImpl): GetCarImagesRepository

    @Binds
    abstract fun bindGetCarImagesRemoteDataSource(dataSource: GetCarImagesRemoteDataSourceImpl): GetCarImagesRemoteDataSource

}