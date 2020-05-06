package com.quantrics.finch.global.base

import com.quantrics.finch.global.api.ApiClient
import com.quantrics.finch.global.api.ApiService

abstract class BasePresenter<V : BaseView.ActivityFragment>(protected val view: V) {

    protected val api: ApiService = ApiClient.instance.service()
}