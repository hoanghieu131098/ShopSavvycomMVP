package com.example.shopsavvycommvp.ui.detailorder.view

import com.example.shopsavvycommvp.data.network.request.Order
import com.example.shopsavvycommvp.ui.base.view.MVPView

interface DetailOrderMVPView: MVPView {
    fun onReponseDetailOrder(order: Order)
}