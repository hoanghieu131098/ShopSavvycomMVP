package com.example.shopsavvycommvp.ui.admin.presenter

import com.example.shopsavvycommvp.ui.admin.interactor.AdminActivityMVPInteractor
import com.example.shopsavvycommvp.ui.admin.view.AdminActivityMVPView
import com.example.shopsavvycommvp.ui.base.presenter.MVPPresenter

interface AdminActivityMVPPresenter<V: AdminActivityMVPView,I: AdminActivityMVPInteractor>:
    MVPPresenter<V, I> {
    fun getProductToRcv()
    fun removeProduct(key: Int)
}