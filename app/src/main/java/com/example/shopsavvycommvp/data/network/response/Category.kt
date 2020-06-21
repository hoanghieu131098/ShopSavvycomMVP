package com.example.shopsavvycommvp.data.network.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Category(val id: Int, var name: String, var image: String) : Serializable,BaseModel(){
    constructor(): this(0,"","")
}