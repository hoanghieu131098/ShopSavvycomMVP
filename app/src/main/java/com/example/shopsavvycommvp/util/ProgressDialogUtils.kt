package com.example.shopsavvycommvp.util

import android.app.Activity
import android.app.ProgressDialog
import com.example.shopsavvycommvp.R

class ProgressDialogUtils {
    // default style
    private var progressStyle = android.R.style.Widget_Material_Light_ProgressBar_Large

    /**
     * @param progressStyle the progressStyle to set
     */
    fun setProgressStyle(progressStyle: Int): ProgressDialogUtils {
        this.progressStyle = progressStyle
        return this
    }

    /**
     * set message
     *
     * @param message
     * @return
     */
    fun setMessage(message: String): ProgressDialogUtils {
        //        this.message = message;
        return this
    }

    /**
     * set progress bar
     *
     * @param percent
     */
    fun setProgressBar(percent: Int): ProgressDialogUtils {
        try {
            if (progressDialog != null) progressDialog!!.progress = percent
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return this
    }

    /**
     * show progress bar
     *
     * @param context
     */
    fun show(context: Activity) {
        try {
            dismiss()
            // init new instance
            progressDialog = ProgressDialog(context, R.style.DialogTheme)
            //            if (!StringUtils.isEmpty(message)) progressDialog.setMessage(message);
            progressDialog!!.setCancelable(false)
            progressDialog!!.setCanceledOnTouchOutside(false)
            //            progressDialog.setProgressStyle(progressStyle);
            progressDialog!!.progress = 0
            if (!ActivityUtils.isFinish(context)) progressDialog!!.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * dismiss dialog
     */
    fun dismiss() {
        try {
            if (progressDialog != null) {
                progressDialog!!.dismiss()
                progressDialog = null
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    companion object {

        private var progressDialog: ProgressDialog? = null


        val instance: ProgressDialogUtils
            get() = ProgressDialogUtils()
    }
}