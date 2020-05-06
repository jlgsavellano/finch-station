package com.quantrics.finch.global.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.viewpager.widget.PagerAdapter
import com.quantrics.finch.BR

abstract class BasePagerAdapter<B : ViewDataBinding, T>(protected val context: Context,
                                                        private val layoutRes: Int,
                                                        protected var items: List<T>) : PagerAdapter() {

    protected lateinit var binding: B

    abstract fun onViewCreated(item: T)

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), layoutRes, container, false)
        binding.setVariable(BR.handler, this)
        onViewCreated(items[position])
        container.addView(binding.root)
        return binding.root
    }

    protected fun getPosition(item: T): Int = items.indexOf(item)

    override fun getCount(): Int = items.size

    override fun isViewFromObject(view: View, obj: Any): Boolean = view == obj

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }
}