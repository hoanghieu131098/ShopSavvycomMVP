package com.example.shopsavvycommvp.data.network.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Reply(
    @SerializedName("id") val id: Int,
    @SerializedName("username") val username: String,
    @SerializedName("image") val image: String,
    @SerializedName("content") val content: String,
    @SerializedName("idComment") val idComment: Int
) : Serializable,
    BaseModel()