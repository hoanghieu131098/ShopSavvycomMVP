package com.example.shopsavvycommvp.ui.detailproduct.fragments.inforfragment.presenter

import com.example.shopsavvycommvp.ui.base.presenter.BasePresenter
import com.example.shopsavvycommvp.ui.detailproduct.fragments.inforfragment.interactor.InforFragmentMVPInteractor
import com.example.shopsavvycommvp.ui.detailproduct.fragments.inforfragment.view.InforFragmentMVPView
import com.example.shopsavvycommvp.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class InforFragmentPresenter @Inject internal constructor(
    interactor: InforFragmentMVPInteractor,
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
): BasePresenter<InforFragmentMVPView,InforFragmentMVPInteractor>(interactor,schedulerProvider,disposable),
InforFragmentMVPPresenter{
}