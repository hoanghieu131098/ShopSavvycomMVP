package com.example.shopsavvycommvp.ui.main.fragments.cartfragment.view

import com.example.shopsavvycommvp.data.network.request.Cart
import com.example.shopsavvycommvp.ui.base.view.MVPView

interface CartFragmentMVPView : MVPView {
    fun onResponse(data: ArrayList<Cart>)
    fun onFailed(msg : String)

    fun onResponseUpdatetotalProductCart(msg: String)
    fun onResponseRemoveProductCart(msg: String)
}