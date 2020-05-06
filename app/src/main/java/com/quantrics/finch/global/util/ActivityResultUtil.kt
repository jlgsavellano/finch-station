package com.quantrics.finch.global.util

import android.app.Activity
import android.content.Intent

open class ActivityResultUtil(private val activity: Activity, private val handler: Handler) {

    fun pushOk() {
        pushOk(null)
        activity.finish()
    }

    fun pushOk(intent: Intent?) {
        activity.setResult(Activity.RESULT_OK, intent)
        activity.finish()
    }

    fun pushCancel() {
        pushCancel(null)
        activity.finish()
    }

    fun pushCancel(intent: Intent?) {
        activity.setResult(Activity.RESULT_CANCELED, intent)
        activity.finish()
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK)
            handler.onResultOk(requestCode, data)
        else if (resultCode == Activity.RESULT_CANCELED)
            handler.onResultCanceled()
    }

    interface Handler {
        fun onResultOk(requestCode: Int, data: Intent?) {}
        fun onResultCanceled() {}
    }
}