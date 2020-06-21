package com.example.shopsavvycommvp.data.network.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Product(var id: Int,var idCategory: Int, var name:String, var price:Float, var description:String, var dataimage:ArrayList<String>?, var comments: ArrayList<Comment>?) :  Serializable,BaseModel(){
    constructor(): this(0,0,"", 0f,"",null, null)
}