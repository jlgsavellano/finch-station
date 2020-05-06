package com.quantrics.finch.main.adapter

import android.content.Context
import com.quantrics.finch.R
import com.quantrics.finch.databinding.AdapterStopTimeBinding
import com.quantrics.finch.global.Constant
import com.quantrics.finch.global.base.BaseRecyclerAdapter
import com.quantrics.finch.global.util.TextUtil
import com.quantrics.finch.model.StopTimeModel

class StopTimeAdapter(context: Context, stopTimes: ArrayList<StopTimeModel>) : BaseRecyclerAdapter<AdapterStopTimeBinding,
        StopTimeModel>(context, stopTimes, R.layout.adapter_stop_time) {

    override fun onViewCreated(item: StopTimeModel) {
        binding.stopTime = item
        val date = TextUtil.parseDate(item.departureTime + "m", "h:mma")
        binding.departureTimestampStopTime.text = TextUtil.formatDate(date, Constant.TIME_FORMAT)
    }

}