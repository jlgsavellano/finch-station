package com.quantrics.finch.global.base

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentTransaction
import com.quantrics.finch.BR
import com.quantrics.finch.global.api.ApiError
import com.quantrics.finch.global.util.ActivityResultUtil
import com.quantrics.finch.global.util.PermissionUtil

abstract class BaseActivity<B : ViewDataBinding, P : BasePresenter<V>, V : BaseView.ActivityFragment> :
    AppCompatActivity(), BaseView.ActivityFragment, ActivityResultUtil.Handler, PermissionUtil.Handler {

    protected lateinit var binding: B

    protected abstract val presenter: P

    lateinit var permission: PermissionUtil

    lateinit var activityResult: ActivityResultUtil

    protected open val fragmentViewId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.setVariable(BR.handler, this)

        permission = PermissionUtil(this, this)
        activityResult = ActivityResultUtil(this, this)

        onViewCreated()
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permission.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        activityResult.onActivityResult(requestCode, resultCode, data)
    }

    inline fun <reified A : BaseActivity<*, *, *>> goTo(bundle: Bundle = Bundle(),
                                                        clearStack: Boolean = false) {
        open<A>(bundle)
        if (!clearStack) finish()
        else finishAffinity()
    }

    inline fun <reified A : BaseActivity<*, *, *>> open(bundle: Bundle = Bundle()) {
        startActivity(Intent(this, A::class.java).putExtras(bundle))
    }

    inline fun <reified A : BaseActivity<*, *, *>> openForResult(requestCode: Int,
                                                                 bundle: Bundle = Bundle()) {
        startActivityForResult(Intent(this, A::class.java).putExtras(bundle), requestCode)
    }

    protected fun openFragment(fragment: BaseFragment<*, *, *>, backStack: String) {
        supportFragmentManager.also { manager ->
            manager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .apply {

                    manager.fragments.forEach { fragment ->
                        if (fragment.isVisible) hide(fragment)
                    }

                    manager.findFragmentByTag(backStack)?.let { fragment ->
                        show(fragment)
                    } ?: run {
                        add(fragmentViewId, fragment, backStack)
                    }

                }.commitNow()
        }
    }

    override fun hasConnectivity(): Boolean {
        val connectivity = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivity.activeNetworkInfo?.isConnectedOrConnecting ?: run { false }
    }

    override fun onConnecting() {

    }

    override fun onConnectionSuccess() {

    }

    override fun onNoConnection() {

    }

    override fun onConnectionException(message: String) {

    }

    override fun onClientError(code: Int, message: String, body: ApiError?) {

    }

    override fun onServerError(code: Int, message: String) {

    }
}