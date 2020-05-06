package com.quantrics.finch.global.api

interface ApiHandler {

    fun hasConnectivity(): Boolean

    fun onConnecting() {}

    fun onNoConnection() {}

    fun onConnectionSuccess() {}

    fun onClientError(code: Int, message: String, body: ApiError?) {}

    fun onServerError(code: Int, message: String) {}

    fun onConnectionException(message: String) {}
}