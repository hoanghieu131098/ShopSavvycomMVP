package com.example.shopsavvycommvp.ui.notiorder.fragments.cholayhang.presenter

import com.example.shopsavvycommvp.ui.base.presenter.BasePresenter
import com.example.shopsavvycommvp.ui.notiorder.fragments.cholayhang.interactor.ChoLayHangMVPInteractor
import com.example.shopsavvycommvp.ui.notiorder.fragments.cholayhang.view.ChoLayHangMVPView
import com.example.shopsavvycommvp.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ChoLayHangPresenter @Inject internal constructor(
    interactor: ChoLayHangMVPInteractor,
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
): BasePresenter<ChoLayHangMVPView,ChoLayHangMVPInteractor>(interactor,schedulerProvider,disposable),ChoLayHangMVPPresenter{
}