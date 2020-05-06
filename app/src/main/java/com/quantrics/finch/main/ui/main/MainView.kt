package com.quantrics.finch.main.ui.main

import com.quantrics.finch.global.base.BaseView
import com.quantrics.finch.model.StationModel

interface MainView : BaseView.ActivityFragment {

    fun onFinchStationResponse(station: StationModel)
}
