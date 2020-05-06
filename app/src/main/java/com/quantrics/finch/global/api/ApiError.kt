package com.quantrics.finch.global.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class ApiError(

    @Expose
    @SerializedName("message")
    val message: String = "")