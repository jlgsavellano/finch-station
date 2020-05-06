package com.quantrics.finch.global.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

abstract class ApiCallback<T>(call: Call<T>, private val handler: ApiHandler) : Callback<T> {

    init {
        if (handler.hasConnectivity()) handler.onConnecting()
        else call.cancel()
    }

    abstract fun onSuccess(code: Int, message: String, response: T?)

    override fun onResponse(call: Call<T>, response: Response<T>) {
        with(response) {
            if (isSuccessful) {
                handler.onConnectionSuccess()
                body().let { onSuccess(code(), message(), it) }
            } else {
                when (code()) {
                    in 400 until 500 -> handler.onClientError(code(), message(), errorBody()?.parse())
                    in 500 until 600 -> handler.onServerError(code(), message())
                    else -> handler.onConnectionException(message())
                }
            }
        }
    }

    override fun onFailure(call: Call<T>, throwable: Throwable) {
        when (throwable) {
            is IOException -> handler.onNoConnection()
            else -> handler.onConnectionException(throwable.message!!)
        }
    }

    private fun ResponseBody.parse(): ApiError? = let {
        ApiClient.instance.gson.fromJson(it.string(), ApiError::class.java)
    }
}