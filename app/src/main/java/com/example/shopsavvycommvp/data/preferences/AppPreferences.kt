package com.example.shopsavvycommvp.data.preferences

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor

open class AppPreferences
/**
 * @param context
 */
@SuppressLint("CommitPrefEdits")
constructor(context: Context, shareName: String) {

    // variable
    private var appSharedPrefs: SharedPreferences? = null
    private var prefsEditor: Editor? = null

    init {
        try {
            appSharedPrefs = context.getSharedPreferences(
                shareName,
                Context.MODE_PRIVATE
            )
            prefsEditor = appSharedPrefs!!.edit()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * put value int
     *
     * @param key
     * @param value
     */
    fun putInt(key: String, value: Int): AppPreferences {
        try {
            prefsEditor!!.putInt(key, value)
            prefsEditor!!.commit()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return this
    }

    /**
     * get value int
     *
     * @param key
     * @return
     */
    fun getInt(key: String): Int {
        try {
            return appSharedPrefs!!.getInt(key, 0)
        } catch (e: Exception) {
            return 0
        }

    }

    /**
     * put value long
     *
     * @param key
     * @param value
     */
    fun putLong(key: String, value: Long): AppPreferences {
        try {
            prefsEditor!!.putLong(key, value)
            prefsEditor!!.commit()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return this
    }

    /**
     * get value long
     *
     * @param key
     * @return
     */
    fun getLong(key: String): Long {
        try {
            return appSharedPrefs!!.getLong(key, 0)
        } catch (e: Exception) {
            return 0
        }

    }

    /**
     * put value boolean
     *
     * @param key
     * @param value
     */
    fun putBoolean(key: String, value: Boolean): AppPreferences {
        try {
            prefsEditor!!.putBoolean(key, value)
            prefsEditor!!.commit()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return this
    }

    /**
     * get value boolean
     *
     * @param key
     * @return
     */
    @JvmOverloads
    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        try {
            return appSharedPrefs!!.getBoolean(key, defaultValue)
        } catch (e: Exception) {
            return false
        }

    }

    /**
     * put value String
     *
     * @param key
     * @param value
     */
    fun putString(key: String, value: String): AppPreferences {
        try {
            prefsEditor!!.putString(key, value)
            prefsEditor!!.commit()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return this
    }


    /**
     * get value String
     *
     * @param key
     * @return
     */
    @JvmOverloads
    fun getString(key: String, defaultValue: String = ""): String? {
        try {
            return appSharedPrefs!!.getString(key, defaultValue)
        } catch (e: Exception) {
            return ""
        }

    }


    /**
     * clear all cache in SharedPreference
     */
    fun clearCache(): AppPreferences {
        try {
            prefsEditor!!.clear().commit()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return this
    }

    companion object {

        private val KEY_PASSWORD_ENCRYPT = "123456789poiuytrewq"

        // instance
        private var instance: AppPreferences? = null

        /**
         * getInstance
         *
         * @param context
         */
        fun getInstance(context: Context, shareName: String): AppPreferences {
            if (instance == null) instance = AppPreferences(context, shareName)

            return instance as AppPreferences
        }
    }
}