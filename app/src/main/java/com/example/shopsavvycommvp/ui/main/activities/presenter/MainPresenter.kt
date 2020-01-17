package com.example.shopsavvycommvp.ui.main.activities.presenter

import com.example.shopsavvycommvp.ui.base.presenter.BasePresenter
import com.example.shopsavvycommvp.ui.main.activities.interactor.MainMVPInteractor
import com.example.shopsavvycommvp.ui.main.activities.view.MainMVPView
import com.example.shopsavvycommvp.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainPresenter<V : MainMVPView, I : MainMVPInteractor> @Inject internal constructor(
    interactor: I,
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V, I>(
    interactor = interactor,
    schedulerProvider = schedulerProvider,
    compositeDisposable = disposable
), MainMVPPresenter<V, I> {
}