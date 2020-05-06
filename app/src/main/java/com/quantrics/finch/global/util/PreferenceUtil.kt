package com.quantrics.finch.global.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class PreferenceUtil constructor(context: Context) {

    private var preference = PreferenceManager.getDefaultSharedPreferences(context)

    companion object {

        @get:Synchronized
        lateinit var instance: PreferenceUtil

        fun createInstance(context: Context) {
            instance = PreferenceUtil(context)
        }
    }

    private inline operator fun <reified T> SharedPreferences.set(key: String, value: T) =
        when (value) {
            is Boolean -> edit().putBoolean(key, value).apply()
            is String -> edit().putString(key, value).apply()
            is Float -> edit().putFloat(key, value).apply()
            is Long -> edit().putLong(key, value).apply()
            is Int -> edit().putInt(key, value).apply()
            else -> throw TypeCastException()
        }

    private inline operator fun <reified T> SharedPreferences.get(key: String, default: T) =
        when (default) {
            is Boolean -> getBoolean(key, default) as T
            is String -> getString(key, default) as T
            is Float -> getFloat(key, default) as T
            is Long -> getLong(key, default) as T
            is Int -> getInt(key, default) as T
            else -> throw TypeCastException()
        }
}