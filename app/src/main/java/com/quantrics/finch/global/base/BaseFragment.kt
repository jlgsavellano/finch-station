package com.quantrics.finch.global.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.quantrics.finch.BR
import com.quantrics.finch.global.api.ApiError

abstract class BaseFragment<B : ViewDataBinding, P : BasePresenter<V>, V : BaseView.ActivityFragment> :
    Fragment(), BaseView.ActivityFragment {

    protected lateinit var binding: B

    protected abstract val presenter: P

    protected val mvpActivity: BaseActivity<*, *, *> = activity as BaseActivity<*, *, *>

    companion object {
        inline fun <reified F : BaseFragment<*, *, *>> newInstance(bundle: Bundle = Bundle()): F =
            F::class.java.newInstance().apply { setArguments(bundle) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.setVariable(BR.handler, this)

        retainInstance = true
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreated()
    }

    override fun hasConnectivity(): Boolean = mvpActivity.hasConnectivity()

    override fun onConnecting() {
        mvpActivity.onConnecting()
    }

    override fun onNoConnection() {
        mvpActivity.onNoConnection()
    }

    override fun onConnectionSuccess() {
        mvpActivity.onConnectionSuccess()
    }

    override fun onClientError(code: Int, message: String, body: ApiError?) {
        mvpActivity.onClientError(code, message, body)
    }

    override fun onServerError(code: Int, message: String) {
        mvpActivity.onServerError(code, message)
    }

    override fun onConnectionException(message: String) {
        mvpActivity.onConnectionException(message)
    }
}