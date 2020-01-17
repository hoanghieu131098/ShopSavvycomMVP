package com.example.shopsavvycommvp.ui.notiorder.fragments.trahang.presenter

import com.example.shopsavvycommvp.ui.base.presenter.BasePresenter
import com.example.shopsavvycommvp.ui.notiorder.fragments.trahang.interactor.TraHangMVPInteractor
import com.example.shopsavvycommvp.ui.notiorder.fragments.trahang.view.TraHangMVPView
import com.example.shopsavvycommvp.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class TraHangPresenter @Inject internal  constructor(
    interactor: TraHangMVPInteractor,
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
): BasePresenter<TraHangMVPView,TraHangMVPInteractor>(interactor,schedulerProvider,disposable),TraHangMVPPresenter{
}