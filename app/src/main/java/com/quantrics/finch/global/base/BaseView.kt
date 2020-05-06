package com.quantrics.finch.global.base

import androidx.appcompat.app.AlertDialog
import com.quantrics.finch.global.api.ApiHandler

abstract class BaseView {

    interface ActivityFragment : ApiHandler {

        val layoutId: Int

        fun onViewCreated()
    }

    interface Dialog {

        val builder: AlertDialog.Builder

        val layoutId: Int

        fun onViewCreated()
    }
}