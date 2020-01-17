package com.example.shopsavvycommvp.ui.base.interactor

import android.text.TextUtils
import com.example.shopsavvycommvp.data.network.AppApi
import com.example.shopsavvycommvp.data.preferences.MvpPreferences

open class BaseInteractor() : MVPInteractor {

    protected lateinit var mvpPreferences: MvpPreferences
    protected lateinit var appApi: AppApi

    constructor(preferenceHelper: MvpPreferences, apiHelper: AppApi) : this() {
        this.mvpPreferences = preferenceHelper
        this.appApi = apiHelper
    }
    constructor(apihelper: AppApi) : this(){
        this.appApi = apihelper
    }

    override fun issUserLoggedIn() = !TextUtils.isEmpty(this.mvpPreferences.emailUser)
}