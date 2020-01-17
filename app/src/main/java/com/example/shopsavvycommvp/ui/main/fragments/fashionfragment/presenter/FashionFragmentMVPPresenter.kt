package com.example.shopsavvycommvp.ui.main.fragments.fashionfragment.presenter

import com.example.shopsavvycommvp.ui.base.presenter.MVPPresenter
import com.example.shopsavvycommvp.ui.main.fragments.fashionfragment.interactor.FashionFragmentMVPInteractor
import com.example.shopsavvycommvp.ui.main.fragments.fashionfragment.view.FashionFragmentMVPView

import com.example.shopsavvycommvp.ui.main.fragments.userfragment.view.UserFragmentMVPView

interface FashionFragmentMVPPresenter: MVPPresenter<FashionFragmentMVPView, FashionFragmentMVPInteractor> {
}