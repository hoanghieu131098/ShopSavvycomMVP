package com.example.shopsavvycommvp.data.network.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Reply(
     val id: Int,
     val username: String,
     val image: String?,
     val content: String
) : Serializable,
    BaseModel(){
    constructor(): this(0,"",null, "")
}