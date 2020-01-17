package com.example.shopsavvycommvp.ui.splash

import com.example.shopsavvycommvp.ui.splash.interactor.SplashInteractor
import com.example.shopsavvycommvp.ui.splash.interactor.SplashMVPInteractor
import com.example.shopsavvycommvp.ui.splash.presenter.SplashMVPPresenter
import com.example.shopsavvycommvp.ui.splash.presenter.SplashPresenter
import com.example.shopsavvycommvp.ui.splash.view.SplashMVPView
import dagger.Module
import dagger.Provides

@Module
class SplashActivityModule {
    @Provides
    internal fun provideSplashInteractor(interactor: SplashInteractor): SplashMVPInteractor = interactor
    @Provides
    internal  fun provideSplashPresenter(presenter: SplashPresenter<SplashMVPView,SplashMVPInteractor>)
    : SplashMVPPresenter<SplashMVPView,SplashMVPInteractor> = presenter
}