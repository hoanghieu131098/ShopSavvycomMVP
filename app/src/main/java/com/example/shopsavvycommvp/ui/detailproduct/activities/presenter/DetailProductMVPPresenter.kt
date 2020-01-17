package com.example.shopsavvycommvp.ui.detailproduct.activities.presenter

import com.example.shopsavvycommvp.data.network.request.Cart
import com.example.shopsavvycommvp.ui.base.presenter.MVPPresenter
import com.example.shopsavvycommvp.ui.detailproduct.activities.interactor.DetailProductMVPInteractor
import com.example.shopsavvycommvp.ui.detailproduct.activities.view.DetailProductMVPView

interface DetailProductMVPPresenter<V: DetailProductMVPView,I: DetailProductMVPInteractor>: MVPPresenter<V,I> {
    fun updateCart(cart: Cart)
}