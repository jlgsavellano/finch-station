package com.quantrics.finch.global.base

import android.app.Application
import com.quantrics.finch.global.api.ApiClient
import com.quantrics.finch.global.util.PreferenceUtil

class BaseApplication : Application() {

    companion object {
        
        @get:Synchronized
        lateinit var instance: BaseApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        ApiClient.createInstance()
        PreferenceUtil.createInstance(this)
    }
}