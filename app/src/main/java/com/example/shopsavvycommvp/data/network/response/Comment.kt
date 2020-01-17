package com.example.shopsavvycommvp.data.network.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Comment(@SerializedName("id") val id: Int,
                   @SerializedName("username") val username: String,
                   @SerializedName("image") val image: String,
                   @SerializedName("content") val content: String,
                   @SerializedName("review") val review: Int,
                   @SerializedName("idProduct") val idProduct: Int,
                   @SerializedName("reply") val reply: ArrayList<Reply>?):Serializable,
    BaseModel()