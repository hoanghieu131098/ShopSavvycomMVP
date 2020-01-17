package com.example.shopsavvycommvp.data.network.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Product(
    @SerializedName("id") var id:String,
    @SerializedName("name") var name:String,
    @SerializedName("price") var price:Float,
    @SerializedName("description") var description:String,
    @SerializedName("images") var dataimage:ArrayList<String>?)
    :  Serializable,BaseModel(){
    constructor(): this("","",0f,"",null)
}