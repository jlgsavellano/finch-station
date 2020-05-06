package com.quantrics.finch.main.ui.main

import android.os.Bundle
import com.quantrics.finch.R
import com.quantrics.finch.databinding.ActivityMainBinding
import com.quantrics.finch.global.base.BaseActivity
import com.quantrics.finch.main.adapter.StopAdapter
import com.quantrics.finch.main.ui.stop_time.StopTimeActivity
import com.quantrics.finch.model.RouteModel
import com.quantrics.finch.model.StationModel

class MainActivity : BaseActivity<ActivityMainBinding, MainPresenter, MainView>(), MainView, StopAdapter.OnRouteClickListener {

    override val presenter get() = MainPresenter(this)

    override val layoutId get() = R.layout.activity_main

    override fun onViewCreated() {
        presenter.requestFinchStation()
    }

    override fun onFinchStationResponse(station: StationModel) {
        binding.listStop.adapter = StopAdapter(this, this, ArrayList(station.stops))
    }

    override fun onRouteClicked(route: RouteModel) {
        val bundle = Bundle().apply {
            putString(StopTimeActivity.ROUTE_NAME_KEY, route.name)
            putParcelableArrayList(StopTimeActivity.STOP_TIMES_KEY, ArrayList(route.stopTimes))
        }

        open<StopTimeActivity>(bundle)
    }
}
