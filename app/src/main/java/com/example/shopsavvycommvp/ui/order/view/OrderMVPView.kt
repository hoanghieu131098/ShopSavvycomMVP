package com.example.shopsavvycommvp.ui.order.view

import com.example.shopsavvycommvp.data.network.request.Order
import com.example.shopsavvycommvp.ui.base.view.MVPView

interface OrderMVPView: MVPView {
    fun orderFailed(msg: String)
    fun orderSuccess(msg: String)

    fun checkInforFail(msg: String)
    fun checkInforSuccess(lastOrder: Order)

    fun onReponseRemoveCart(msg: String)
}