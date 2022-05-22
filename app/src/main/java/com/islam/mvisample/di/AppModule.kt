package com.islam.mvisample.di

import com.islam.mvisample.BuildConfig
import com.islam.mvisample.common.TIME_OUT_IN_SECONDS
import com.islam.mvisample.data.remote.AppService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Singleton
    @Provides
    fun provideAPI(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): AppService {

        val okkHttpclient = OkHttpClient.Builder()
            .readTimeout(TIME_OUT_IN_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT_IN_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT_IN_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .client(okkHttpclient)
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        return retrofit.create(AppService::class.java)

    }

}