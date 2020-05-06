package com.quantrics.finch.global.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ihsanbal.logging.LoggingInterceptor
import com.quantrics.finch.BuildConfig
import com.quantrics.finch.global.Constant
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

class ApiClient {

    companion object {

        private const val TIMEOUT_DURATION = 15L

        @get:Synchronized
        lateinit var instance: ApiClient

        fun createInstance() {
            instance = ApiClient()
        }
    }

    fun service(): ApiService = retrofit.create(ApiService::class.java)

    val gson: Gson
        get() = GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setDateFormat(Constant.DATE_TIME_PARSE)
            .setPrettyPrinting()
            .create()

    private val retrofit
        get() = Retrofit.Builder()
            .baseUrl(BuildConfig.API_SERVER)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(safeClient)
            .build()

    private val okHttpBuilder
        get() = OkHttpClient.Builder()
            .readTimeout(TIMEOUT_DURATION, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_DURATION, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_DURATION, TimeUnit.SECONDS)

            .addInterceptor {
                it.proceed(it.request().newBuilder().apply {
                    addHeader("User-Agent", "${BuildConfig.APP_NAME}/${BuildConfig.VERSION_NAME} ${System.getProperty("http.agent")}")
                    addHeader("Accept-Language", "${Locale.getDefault().language}-${Locale.getDefault().country}")
                }.build())
            }

            .addInterceptor(LoggingInterceptor.Builder()
                    .loggable(BuildConfig.DEBUG)
                    .request("apiClient_request")
                    .response("apiClient_response")
                    .log(Platform.INFO)
                    .build())

    private val safeClient get() = okHttpBuilder.build()
}