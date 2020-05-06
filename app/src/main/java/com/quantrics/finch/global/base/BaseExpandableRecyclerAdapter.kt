package com.quantrics.finch.global.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.bignerdranch.expandablerecyclerview.ChildViewHolder
import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter
import com.bignerdranch.expandablerecyclerview.ParentViewHolder
import com.bignerdranch.expandablerecyclerview.model.Parent
import com.quantrics.finch.BR

abstract class BaseExpandableRecyclerAdapter<PB : ViewDataBinding, CB: ViewDataBinding,
        P : Parent<C>, C>(protected val context: Context, list: ArrayList<P>, private val parentLayoutRes: Int, private val childLayoutRes: Int) :
            ExpandableRecyclerAdapter<P, C, BaseExpandableRecyclerAdapter<PB, CB, P, C>.PViewHolder,
            BaseExpandableRecyclerAdapter<PB, CB, P, C>.CViewHolder>(list) {

    protected lateinit var parentBinding: PB

    protected lateinit var childBinding: CB

    protected abstract fun onParentViewCreated(item: P)

    protected abstract fun onChildViewCreated(item: C)

    override fun onCreateParentViewHolder(parentViewGroup: ViewGroup, viewType: Int) =
        PViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parentViewGroup.context), parentLayoutRes, parentViewGroup, false))

    override fun onCreateChildViewHolder(childViewGroup: ViewGroup, viewType: Int) =
        CViewHolder(DataBindingUtil.inflate(LayoutInflater.from(childViewGroup.context), childLayoutRes, childViewGroup, false))

    override fun onBindParentViewHolder(parentViewHolder: PViewHolder, parentPosition: Int, parent: P) {
        parentBinding = parentViewHolder.binding
        parentBinding.setVariable(BR.handler, this)
        onParentViewCreated(parent)
    }

    override fun onBindChildViewHolder(childViewHolder: CViewHolder, parentPosition: Int, childPosition: Int, child: C) {
        childBinding = childViewHolder.binding
        childBinding.setVariable(BR.handler, this)
        onChildViewCreated(child)
    }

    inner class PViewHolder(val binding: PB) : ParentViewHolder<P, C>(binding.root)

    inner class CViewHolder(val binding: CB) : ChildViewHolder<C>(binding.root)
}