package com.quantrics.finch.main.adapter

import android.content.Context
import com.quantrics.finch.R
import com.quantrics.finch.databinding.AdapterRouteBinding
import com.quantrics.finch.databinding.AdapterStopBinding
import com.quantrics.finch.global.base.BaseExpandableRecyclerAdapter
import com.quantrics.finch.model.RouteModel
import com.quantrics.finch.model.StopModel

class StopAdapter(context: Context, private val listener: OnRouteClickListener, stops: ArrayList<StopModel>) : BaseExpandableRecyclerAdapter
        <AdapterStopBinding, AdapterRouteBinding, StopModel, RouteModel>(context, stops, R.layout.adapter_stop, R.layout.adapter_route) {

    override fun onParentViewCreated(item: StopModel) {
        parentBinding.stop = item
    }

    override fun onChildViewCreated(item: RouteModel) {
        childBinding.route = item
    }

    fun onRouteClicked(route: RouteModel) {
        listener.onRouteClicked(route)
    }

    interface OnRouteClickListener {

        fun onRouteClicked(route: RouteModel)
    }
}