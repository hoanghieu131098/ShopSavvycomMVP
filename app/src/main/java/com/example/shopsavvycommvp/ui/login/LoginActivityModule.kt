package com.example.shopsavvycommvp.ui.login

import com.example.shopsavvycommvp.ui.login.interactor.LoginInteractor
import com.example.shopsavvycommvp.ui.login.interactor.LoginMVPInteractor
import com.example.shopsavvycommvp.ui.login.presenter.LoginMVPPresenter
import com.example.shopsavvycommvp.ui.login.presenter.LoginPresenter
import com.example.shopsavvycommvp.ui.login.view.LoginMVPView
import dagger.Module
import dagger.Provides

@Module
class LoginActivityModule {
    @Provides
    internal fun provideLoginInteractor(interactor: LoginInteractor): LoginMVPInteractor = interactor
    @Provides
    internal fun provideLoginPresenter(presenter: LoginPresenter<LoginMVPView,LoginMVPInteractor>):LoginMVPPresenter<LoginMVPView,LoginMVPInteractor> = presenter
}