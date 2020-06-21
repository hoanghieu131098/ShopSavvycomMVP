package com.example.shopsavvycommvp.data.network.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Comment(val id: Int,
                   val username: String,
                   val image: String?,
                   val content: String,
                   val review: Int,
                   val reply: ArrayList<Reply>?):Serializable, BaseModel(){
    constructor(): this(0,"",null, "",0, null)
}