package com.example.shopsavvycommvp.ui.order.view

import com.example.shopsavvycommvp.ui.base.view.MVPView

interface OrderMVPView: MVPView {
    fun orderFailed(msg: String)
    fun orderSuccess(msg: String)

    fun onReponseRemoveCart(msg: String)
}