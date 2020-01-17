package com.example.shopsavvycommvp.ui.login.presenter

import com.example.shopsavvycommvp.ui.base.presenter.BasePresenter
import com.example.shopsavvycommvp.ui.login.interactor.LoginMVPInteractor
import com.example.shopsavvycommvp.ui.login.view.LoginMVPView
import com.example.shopsavvycommvp.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LoginPresenter<V : LoginMVPView, I : LoginMVPInteractor> @Inject internal constructor(
    interactor: I,
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V, I>(interactor, schedulerProvider, disposable), LoginMVPPresenter<V, I> {
}