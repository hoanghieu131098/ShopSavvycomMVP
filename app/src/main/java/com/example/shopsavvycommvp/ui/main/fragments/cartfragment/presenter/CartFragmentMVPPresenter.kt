package com.example.shopsavvycommvp.ui.main.fragments.cartfragment.presenter

import com.example.shopsavvycommvp.ui.base.presenter.MVPPresenter
import com.example.shopsavvycommvp.ui.main.fragments.cartfragment.interactor.CartFragmentMVPInteractor
import com.example.shopsavvycommvp.ui.main.fragments.cartfragment.view.CartFragmentMVPView
import com.example.shopsavvycommvp.ui.main.fragments.userfragment.view.UserFragmentMVPView

interface CartFragmentMVPPresenter : MVPPresenter<CartFragmentMVPView, CartFragmentMVPInteractor> {
    fun getToTalProductItem(total: Int, id: Int)
    fun getClickDeleteItemCart(id: Int)
}