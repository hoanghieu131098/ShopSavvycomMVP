package com.example.shopsavvycommvp.ui.notiorder.fragments.danggiao.presenter

import com.example.shopsavvycommvp.ui.base.presenter.MVPPresenter
import com.example.shopsavvycommvp.ui.notiorder.fragments.danggiao.interactor.DangGiaoMVPInteractor
import com.example.shopsavvycommvp.ui.notiorder.fragments.danggiao.view.DangGiaoMVPView

interface DangGiaoMVPPresenter: MVPPresenter<DangGiaoMVPView,DangGiaoMVPInteractor> {
    fun doloadOrder()
    fun doUpdateActive(id: String)
}