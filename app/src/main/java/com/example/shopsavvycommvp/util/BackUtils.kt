package com.example.shopsavvycommvp.util

import android.app.Activity
import android.os.Handler
import android.view.Gravity

object BackUtils {
    private var isDoubleClick = false

    fun onClickExit(activity: Activity, messageConfirm: String) {
        try {
            // double tap to exit app
            if (BackUtils.isDoubleClick) {
//                System.exit(0)
                activity.finish()
                return
            }

            BackUtils.isDoubleClick = true
            ToastUtils.showToast(activity, messageConfirm, Gravity.BOTTOM)
            Handler().postDelayed({ BackUtils.isDoubleClick = false }, 2000)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}