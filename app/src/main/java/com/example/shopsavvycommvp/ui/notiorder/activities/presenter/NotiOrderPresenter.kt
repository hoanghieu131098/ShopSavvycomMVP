package com.example.shopsavvycommvp.ui.notiorder.activities.presenter

import com.example.shopsavvycommvp.ui.base.presenter.BasePresenter
import com.example.shopsavvycommvp.ui.notiorder.activities.interactor.NotiOrderMVPInteractor
import com.example.shopsavvycommvp.ui.notiorder.activities.view.NotiOrderMVPView
import com.example.shopsavvycommvp.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class NotiOrderPresenter<V : NotiOrderMVPView, I : NotiOrderMVPInteractor> @Inject internal constructor(
    interactor: I,
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V, I>(interactor, schedulerProvider, disposable), NotiOrderMVPPresenter<V, I> {
}