package com.example.shopsavvycommvp.ui.main.fragments.homefragment.presenter


import com.example.shopsavvycommvp.data.network.response.Product
import com.example.shopsavvycommvp.ui.base.presenter.MVPPresenter
import com.example.shopsavvycommvp.ui.main.fragments.homefragment.interactor.HomeFragmentMVPInteractor
import com.example.shopsavvycommvp.ui.main.fragments.homefragment.view.HomeFragmentMVPView

interface HomeFragmentMVPPresenter: MVPPresenter<HomeFragmentMVPView, HomeFragmentMVPInteractor> {
    fun getProductToRcv()
    fun getCategory()
    fun getToTalOrder()
}