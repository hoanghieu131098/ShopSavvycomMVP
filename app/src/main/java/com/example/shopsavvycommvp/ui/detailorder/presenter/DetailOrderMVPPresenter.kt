package com.example.shopsavvycommvp.ui.detailorder.presenter

import com.example.shopsavvycommvp.ui.base.presenter.MVPPresenter
import com.example.shopsavvycommvp.ui.detailorder.interactor.DetailOrderMVPInteractor
import com.example.shopsavvycommvp.ui.detailorder.view.DetailOrderMVPView

interface DetailOrderMVPPresenter<V: DetailOrderMVPView,I: DetailOrderMVPInteractor> : MVPPresenter<V,I> {
    fun setIdOrderLoadDetail(id: String)
}