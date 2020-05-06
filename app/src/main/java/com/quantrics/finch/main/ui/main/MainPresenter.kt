package com.quantrics.finch.main.ui.main

import com.quantrics.finch.global.api.ApiCallback
import com.quantrics.finch.global.base.BasePresenter
import com.quantrics.finch.model.StationModel

class MainPresenter(view: MainView) : BasePresenter<MainView>(view) {

    fun requestFinchStation() {
        api.getFinchStation().apply {
            enqueue(object : ApiCallback<StationModel>(this,view) {
                override fun onSuccess(code: Int, message: String, response: StationModel?) {
                    view.onFinchStationResponse(response!!)
                }
            })
        }
    }
}