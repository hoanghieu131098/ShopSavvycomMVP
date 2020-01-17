package com.example.shopsavvycommvp.data.preferences

import android.content.Context
import com.example.shopsavvycommvp.data.network.response.Account
import com.google.gson.Gson

class MvpPreferences: AppPreferences {

    private var gson: Gson? = null
    val emailUser: String?
        get() = getString(KEY_EMAIL_USER)
    val loggedUser: Account?
        get() {
            val loggedUser = getString(KEY_LOGGED_USER)
            return if(loggedUser!!.isEmpty()){
                null
            } else gson!!.fromJson(loggedUser,Account::class.java)

        }
    /**
     * @param context
     */
    constructor(context: Context) : super(context.applicationContext, context.packageName) {}

    /**
     * @param context
     */
    constructor(context: Context, gson: Gson) : super(context.applicationContext, context.packageName) {
        this.gson = gson
    }

    fun saveLoggedUser(account: Account?) {
        putString(KEY_LOGGED_USER, gson!!.toJson(account))
    }
    fun saveAccessToken(emailUser: String) {
        putString(KEY_EMAIL_USER, emailUser)
    }

    companion object {

        private val KEY_EMAIL_USER = "KEY_EMAIL_USER"
        private val KEY_LOGGED_USER = "KEY_LOGGED_USER"

    }
}