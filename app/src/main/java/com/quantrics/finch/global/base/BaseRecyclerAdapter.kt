package com.quantrics.finch.global.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.quantrics.finch.BR

abstract class BaseRecyclerAdapter<B : ViewDataBinding, T>(protected val context: Context,
                                                           protected var items: ArrayList<T>,
                                                           private val layoutRes: Int) : RecyclerView.Adapter<BaseRecyclerAdapter<B, T>.ViewHolder>() {

    protected lateinit var binding: B

    protected abstract fun onViewCreated(item: T)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutRes, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        binding = holder.binding
        binding.setVariable(BR.handler, this)
        onViewCreated(items[position])
    }

    inner class ViewHolder(val binding: B) : RecyclerView.ViewHolder(binding.root)
}