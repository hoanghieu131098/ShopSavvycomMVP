package com.example.shopsavvycommvp.ui.notiorder.fragments.dahuy.presenter

import com.example.shopsavvycommvp.ui.base.presenter.BasePresenter
import com.example.shopsavvycommvp.ui.notiorder.fragments.dahuy.interactor.DaHuyMVPInteractor
import com.example.shopsavvycommvp.ui.notiorder.fragments.dahuy.view.DaHuyMVPView
import com.example.shopsavvycommvp.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DaHuyPresenter @Inject internal constructor(
    interactor: DaHuyMVPInteractor,
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
): BasePresenter<DaHuyMVPView,DaHuyMVPInteractor>(interactor,schedulerProvider,disposable),DaHuyMVPPresenter{
}