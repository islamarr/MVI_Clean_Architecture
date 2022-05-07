package com.move.mvisample.data.remote

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("message")
    val message: ArrayList<String>? = null,
    @SerializedName("code")
    val code: Long? = null,
    @SerializedName("reason")
    val reason: String? = null,
    @SerializedName("data")
    val data: T? = null
)