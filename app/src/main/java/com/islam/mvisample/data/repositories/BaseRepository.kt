package com.islam.mvisample.data.repositories

import com.islam.mvisample.data.remote.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

/**
 * To handle Http Exceptions Like no internet connection and Time out
 */
interface BaseRepository {

    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): NetworkResponse<T> {
        return withContext(Dispatchers.IO) {
            try {
                NetworkResponse.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        NetworkResponse.Failure(
                            throwable.code(),
                            throwable.response()?.errorBody().toString()
                        )
                    }
                    else -> {
                        NetworkResponse.Failure(0, throwable.message)
                    }
                }
            }
        }
    }

}