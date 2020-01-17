package com.example.shopsavvycommvp.data.network.response

import com.example.shopsavvycommvp.util.AppConstants
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class BaseResponse {
    @Expose
    @SerializedName("status")
    var status: String? = null

    @Expose
    @SerializedName("code")
    var code: String? = null

    @Expose
    @SerializedName("message")
    var message: String? = null

    fun haveError(): Boolean {
        return status!!.endsWith(AppConstants.API_ERROR_STATUS)
    }
}