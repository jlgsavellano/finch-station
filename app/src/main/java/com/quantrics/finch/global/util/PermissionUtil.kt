package com.quantrics.finch.global.util

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

open class PermissionUtil(private val context: Context, private val handler: Handler) {

    companion object {
        const val PERMISSION_ALL = 1000
    }

    private val getAllPermissions =
        context.packageManager.getPackageInfo(context.packageName, PackageManager.GET_PERMISSIONS)
            .requestedPermissions.filter { it.contains("android.permission", true) }.toTypedArray()

    private var requestCode: Int = 0

    fun openSettings() {
        val activity = context as Activity
        activity.startActivity(
            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", context.packageName, null))
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }

    fun checkPermissions(requestCode: Int = PERMISSION_ALL, vararg permissions: String = getAllPermissions): PermissionUtil {
        this.requestCode = requestCode
        if (permissions.isNotEmpty()) {
            val noAccessPermissions = getNoAccess(permissions)
            if (noAccessPermissions.isEmpty()) handler.onPermissionsGranted(requestCode)
            else ask(requestCode, noAccessPermissions)
        }

        return this
    }

    private fun ask(requestCode: Int, permissions: Array<String>) {
        ActivityCompat.requestPermissions(context as Activity, permissions, requestCode)
    }

    private fun getNoAccess(permissions: Array<out String>): Array<String> =
        permissions.filter { ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_DENIED }.toTypedArray()

    private fun isAllGranted(results: IntArray): Boolean =
        results.none { it == PackageManager.PERMISSION_DENIED }

    @TargetApi(Build.VERSION_CODES.M)
    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
        results: IntArray) {
        if (this.requestCode == requestCode) {
            var isPermissionBlocked = false

            if (isAllGranted(results)) {
                handler.onPermissionsGranted(requestCode)
            } else {
                for (permission in permissions) {
                    val activity = context as Activity
                    val isNotBlocked = activity.shouldShowRequestPermissionRationale(permission)
                    val isGranted = ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED

                    if (!isNotBlocked && !isGranted) {
                        handler.onPermissionsBlocked()
                        isPermissionBlocked = true
                        break
                    }
                }

                if (!isPermissionBlocked) handler.onPermissionsDenied()
            }
        }
    }

    interface Handler {
        fun onPermissionsGranted(requestCode: Int) {}
        fun onPermissionsDenied() {}
        fun onPermissionsBlocked() {}
    }
}