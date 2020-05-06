package com.quantrics.finch.global.base

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseDialog<B : ViewDataBinding>(protected val activity: Activity) : BaseView.Dialog {

    protected lateinit var binding: B

    override val builder = AlertDialog.Builder(activity)

    private lateinit var dialog: AlertDialog

    protected open val cancelable = true

    protected open val positiveButtonTitle = ""

    protected open val negativeButtonTitle = ""

    protected open val neutralButtonTitle = ""

    protected open lateinit var positiveOnClick: View.OnClickListener

    protected open lateinit var negativeOnClick: View.OnClickListener

    protected open lateinit var neutralOnClick: View.OnClickListener

    protected open lateinit var positiveButton: Button

    protected open lateinit var negativeButton: Button

    protected open lateinit var neutralButton: Button

    protected open fun onClick(dismiss: () -> Boolean) =
        View.OnClickListener { if (dismiss.invoke()) dialog.dismiss() }

    private fun initView() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(activity), layoutId, null, false)
        
        builder.setView(binding.root)
        builder.setCancelable(cancelable)
        
        when {
            positiveButtonTitle.isNotEmpty() -> builder.setPositiveButton(positiveButtonTitle, null)
            negativeButtonTitle.isNotEmpty() -> builder.setNegativeButton(negativeButtonTitle, null)
            neutralButtonTitle.isNotEmpty() -> builder.setNeutralButton(neutralButtonTitle, null)
        }
        
        onViewCreated()
    }

    protected fun dismiss() = dialog.dismiss()

    open fun show() {
        initView()
        dialog = builder.create()
        dialog.show()

        when {
            positiveButtonTitle.isNotEmpty() -> dialog.getButton(AlertDialog.BUTTON_POSITIVE).apply {
                positiveButton = this
                isAllCaps = false
                setOnClickListener(positiveOnClick)
            }

            negativeButtonTitle.isNotEmpty() -> dialog.getButton(AlertDialog.BUTTON_NEGATIVE).apply {
                negativeButton = this
                isAllCaps = false
                setOnClickListener(negativeOnClick)
            }

            neutralButtonTitle.isNotEmpty() -> dialog.getButton(AlertDialog.BUTTON_NEUTRAL).apply {
                neutralButton = this
                isAllCaps = false
                setOnClickListener(neutralOnClick)
            }
        }
    }
}
