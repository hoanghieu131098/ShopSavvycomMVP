package com.example.shopsavvycommvp.ui.order.presenter

import com.example.shopsavvycommvp.data.network.request.Order
import com.example.shopsavvycommvp.ui.base.presenter.MVPPresenter
import com.example.shopsavvycommvp.ui.order.interactor.OrderMVPInteractor
import com.example.shopsavvycommvp.ui.order.view.OrderMVPView

interface OrderMVPPresenter<V: OrderMVPView,I: OrderMVPInteractor> : MVPPresenter<V,I> {
    fun getOrder(order: Order)
    fun checkInforUserOrder(userID: String)
}