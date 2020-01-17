package com.example.shopsavvycommvp.ui.splash.presenter

import com.example.shopsavvycommvp.ui.base.presenter.BasePresenter
import com.example.shopsavvycommvp.ui.splash.interactor.SplashInteractor
import com.example.shopsavvycommvp.ui.splash.interactor.SplashMVPInteractor
import com.example.shopsavvycommvp.ui.splash.view.SplashMVPView
import com.example.shopsavvycommvp.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SplashPresenter<V : SplashMVPView, I : SplashMVPInteractor> @Inject internal constructor(
    interactor: I,
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V, I>(interactor, schedulerProvider, disposable), SplashMVPPresenter<V, I> {
}