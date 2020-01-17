package com.example.shopsavvycommvp.ui.notiorder.fragments.dagiao.presenter

import com.example.shopsavvycommvp.ui.base.presenter.BasePresenter
import com.example.shopsavvycommvp.ui.notiorder.fragments.dagiao.interactor.DaGiaoMVPInteractor
import com.example.shopsavvycommvp.ui.notiorder.fragments.dagiao.view.DaGiaoMVPView
import com.example.shopsavvycommvp.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DaGiaoPresenter @Inject internal constructor(
    interactor: DaGiaoMVPInteractor,
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<DaGiaoMVPView, DaGiaoMVPInteractor>(
    interactor,
    schedulerProvider,
    disposable
),
    DaGiaoMVPPresenter  {
}