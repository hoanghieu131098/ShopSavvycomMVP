package com.example.shopsavvycommvp.ui.splash.presenter

import com.example.shopsavvycommvp.ui.base.presenter.MVPPresenter
import com.example.shopsavvycommvp.ui.splash.interactor.SplashMVPInteractor
import com.example.shopsavvycommvp.ui.splash.view.SplashMVPView

interface SplashMVPPresenter<V: SplashMVPView,I: SplashMVPInteractor>: MVPPresenter<V,I> {
}