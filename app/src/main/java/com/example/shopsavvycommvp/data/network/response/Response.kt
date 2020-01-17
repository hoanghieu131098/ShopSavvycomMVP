package com.example.shopsavvycommvp.data.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Response<T> : BaseResponse() {
    @Expose
    @SerializedName("data")
    var data: T? = null
}