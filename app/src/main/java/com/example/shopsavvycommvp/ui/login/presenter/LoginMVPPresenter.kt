package com.example.shopsavvycommvp.ui.login.presenter

import com.example.shopsavvycommvp.ui.base.presenter.MVPPresenter
import com.example.shopsavvycommvp.ui.login.interactor.LoginMVPInteractor
import com.example.shopsavvycommvp.ui.login.view.LoginMVPView

interface LoginMVPPresenter<V: LoginMVPView,I: LoginMVPInteractor>: MVPPresenter<V,I> {
}