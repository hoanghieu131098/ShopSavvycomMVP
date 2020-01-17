package com.example.shopsavvycommvp.data.network.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Category(
    @SerializedName("Id") val id:
    Int, @SerializedName("Name") val name:
    String, @SerializedName("Image") val image: String
) : Serializable,BaseModel()