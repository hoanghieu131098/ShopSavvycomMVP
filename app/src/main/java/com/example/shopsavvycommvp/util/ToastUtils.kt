package com.example.shopsavvycommvp.util

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.example.shopsavvycommvp.R
import javax.inject.Inject

object ToastUtils {

    fun showToastNetworkUnAvailable(context: Context) {
        try {
            val toast = Toast.makeText(context, context.getText(R.string.error_no_network_connection), Toast.LENGTH_LONG)
            toast.setGravity(Gravity.BOTTOM, 0, 0)
            toast.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * @param context
     * @param message
     */
    fun showToast(context: Context, message: String) {
        try {
            val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.BOTTOM, 0, 0)
            toast.show()
        } catch (e: Throwable) {
            e.printStackTrace()
        }

    }

    /**
     * @param context
     * @param message
     * @param gravity
     */
    fun showToast(context: Context, message: String, gravity: Int) {
        try {
            val toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
            toast.setGravity(gravity, 0, 0)
            toast.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun showToast(context: Context, message: String, gravity: Int,
                  xOffset: Int, yOffset: Int) {
        try {
            val toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
            toast.setGravity(gravity, xOffset, yOffset)
            toast.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * @param context
     * @param view
     */
    fun showToast(context: Context, view: View) {
        try {
            val toast = Toast(context)
            toast.setGravity(Gravity.BOTTOM, 0, 0)
            toast.duration = Toast.LENGTH_LONG
            toast.view = view
            toast.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}