package com.example.shopsavvycommvp.data.network.request

import com.example.shopsavvycommvp.data.network.response.BaseModel
import com.example.shopsavvycommvp.data.network.response.Product
import java.io.Serializable

data class Cart(var total: Int,
           var product: Product?):Serializable, BaseModel()
{
    constructor(): this(0, null)
}
