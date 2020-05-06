package com.quantrics.finch.global.api

import com.quantrics.finch.model.StationModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("finch_station.json")
    fun getFinchStation(): Call<StationModel>
}