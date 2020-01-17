package com.example.shopsavvycommvp.data.network.request

import com.example.shopsavvycommvp.data.network.response.BaseModel
import java.io.Serializable


data class Order(var id: String?,
                 var address: String,
                 var phoneNumber: String,
                 var dateTime: Long,
                 var totalMoney: Double,
                 var active: Boolean,
                 var listCart: ArrayList<Cart>?):Serializable, BaseModel() {
    constructor(): this("","","",0,0.0,false,null)
}