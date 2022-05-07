package com.move.islam.data.interceptors

import android.content.Context
import com.move.islam.R
import com.move.islam.core.NoInternetException
import com.move.islam.core.Utils
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ConnectivityInterceptorImpl @Inject constructor(private val context: Context) :
    ConnectivityInterceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        //TODO make sure this is the best approach
        if (!Utils.isOnline(context)) throw NoInternetException(context.resources.getString(R.string.no_internet_connection))
        return chain.proceed(chain.request())
    }

}