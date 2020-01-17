package com.example.shopsavvycommvp.data.network

import android.content.Context
import com.example.shopsavvycommvp.R
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLException

object ErrorHandler {
    val HTTP_INTERNAL_ERROR = 500

    val HTTP_NOT_FOUND = 404

    val HTTP_UNAVAILABLE = 503

    val HTTP_PROXY_ERROR = 502

    fun handle(context: Context, throwable: Throwable?): String {
        //        Throwable cause = throwable.getCause();
        //        if (cause == null) cause = throwable;
        if (!NetworkUtils.isNetworkConnected(context)) {
            return context.getString(R.string.error_no_network_connection)
        }
        if (throwable != null && throwable is HttpException) {
            val code = throwable.code()

            // If unauthorized, UnauthorizedInterceptor handles it
            if (code == HttpsURLConnection.HTTP_UNAUTHORIZED) {
                return context.getString(R.string.error_unauthorized)
            }
            if (code == HTTP_NOT_FOUND || code == HTTP_INTERNAL_ERROR || code == HTTP_UNAVAILABLE || code == HTTP_PROXY_ERROR) {
                return context.getString(R.string.error_server_connection)
            }
        } else if (throwable != null && (throwable is SocketTimeoutException || throwable is UnknownHostException) || throwable is ConnectException ||
            throwable is SocketException || throwable is SSLException
        ) {
            return context.getString(R.string.error_server_connection)
        }
        if(throwable!!.equals("null")){
            return context.getString(R.string.not_item)
        }
        return context.getString(R.string.error_occurred)
    }


    fun isConnectionTimeOut(throwable: Throwable): Boolean {
        var cause: Throwable? = throwable.cause
        if (cause == null) cause = throwable
        return (cause is SocketTimeoutException || cause is UnknownHostException) || cause is ConnectException || cause is SocketException || cause is SSLException
    }
}