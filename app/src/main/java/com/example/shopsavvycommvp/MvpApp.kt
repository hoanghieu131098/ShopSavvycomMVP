package com.example.shopsavvycommvp

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.os.SystemClock
import android.view.View
import com.example.shopsavvycommvp.di.component.DaggerAppComponent
import com.example.shopsavvycommvp.util.AppLogger
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


class MvpApp : Application(), HasActivityInjector {
    var isMuteAudio: Boolean = false
    private var DELAY_TIME_ITEM_CONTINUE_CLICKED: Long = 300
    private var mLastClickTimeListViewItem: Long = 0
    @Inject
    lateinit internal var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>


    override fun activityInjector() = activityDispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }

    fun isValidContinueClick(v: View?, disable: Boolean = true): Boolean {
        //To prevent multiple clicks at time
        if (SystemClock.elapsedRealtime() - mLastClickTimeListViewItem < DELAY_TIME_ITEM_CONTINUE_CLICKED) {
            AppLogger.e("isValidContinueClick-" + "block multi click")
            return false
        }
        if (v != null && v.isEnabled) {
            v.isEnabled = false
            v.postDelayed(Runnable { v.isEnabled = true }, DELAY_TIME_ITEM_CONTINUE_CLICKED)
        }
        mLastClickTimeListViewItem = SystemClock.elapsedRealtime()
        return true
    }

}