package com.example.shopsavvycommvp.util

import android.app.Activity
import android.util.Log

object ActivityUtils {
    private val TAG = ActivityUtils::class.java.simpleName

    /**
     * @param activity
     * @return
     */
    fun isFinish(activity: Activity?): Boolean {
        try {
            if (activity == null || activity.isFinishing) {
                Log.d(TAG, "Activity is finished!")
                return true
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return false
    }

    /**
     * Intent intent = new Intent(mContext, Account.class);
     * intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
     * intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
     * mContext.startActivity(intent);
     *
     *
     * Util.animationActivity(mContext, false);
     *
     * @param activity
     */
    fun animationActivity(activity: Activity) {
//        activity.overridePendingTransition(R.anim.activity_open_right_to_left,
//                R.anim.activity_close_right_to_left)
    }
}