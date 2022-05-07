package com.move.islam.data.remote

/**
 * Common class used by API responses.
 * @param <T> the type of the response object
</T> */
sealed class NetworkResponse<T> {
    data class Success<T>(
        val data: T?,
        val httpCode: Long,
    ) : NetworkResponse<T>()

    data class Failure<T>(
        val httpCode: Long,
        val reason: String? = null,
    ) : NetworkResponse<T>()
}