package com.example.shopsavvycommvp.ui.notiorder.fragments.choxacnhan.presenter

import com.example.shopsavvycommvp.ui.base.presenter.BasePresenter
import com.example.shopsavvycommvp.ui.notiorder.fragments.choxacnhan.interactor.ChoXacNhanMVPInteractor
import com.example.shopsavvycommvp.ui.notiorder.fragments.choxacnhan.view.ChoXacNhanMVPView
import com.example.shopsavvycommvp.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ChoXacNhanPresenter @Inject internal  constructor(
    interactor: ChoXacNhanMVPInteractor,
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
): BasePresenter<ChoXacNhanMVPView,ChoXacNhanMVPInteractor>(interactor,schedulerProvider,disposable),ChoXacNhanMVPPresenter{
}