package com.quantrics.finch.global.base

import android.os.Parcelable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry

interface BaseModel : Observable, Parcelable {

    companion object {

        @Transient
        private val registry = PropertyChangeRegistry()
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        registry.remove(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        registry.add(callback)
    }

    fun notifyChange() {
        registry.notifyCallbacks(this, 0, null)
    }

    fun notifyPropertyChanged(fieldId: Int) {
        registry.notifyCallbacks(this, fieldId, null)
    }
}