package com.quantrics.finch.main.ui.stop_time

import com.quantrics.finch.R
import com.quantrics.finch.databinding.ActivityStopTimeBinding
import com.quantrics.finch.global.base.BaseActivity
import com.quantrics.finch.main.adapter.StopTimeAdapter
import com.quantrics.finch.model.StopTimeModel

class StopTimeActivity : BaseActivity<ActivityStopTimeBinding, StopTimePresenter, StopTimeView>(), StopTimeView {

    override val presenter get() = StopTimePresenter(this)

    override val layoutId get() = R.layout.activity_stop_time

    companion object {
        const val ROUTE_NAME_KEY = "ROUTE_NAME_KEY"

        const val STOP_TIMES_KEY = "STOP_TIMES_KEY"
    }

    override fun onViewCreated() {
        supportActionBar?.apply {
            title = intent.getStringExtra(ROUTE_NAME_KEY)
            setDisplayHomeAsUpEnabled(true)
        }

        val stopTimes = intent.getParcelableArrayListExtra<StopTimeModel>(STOP_TIMES_KEY)
        binding.listStopTimes.adapter = StopTimeAdapter(this, ArrayList(stopTimes!!))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
