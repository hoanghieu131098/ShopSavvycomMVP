package com.example.shopsavvycommvp.ui.helpcentre.presenter

import com.example.shopsavvycommvp.ui.base.presenter.BasePresenter
import com.example.shopsavvycommvp.ui.helpcentre.interactor.HelpCentreFragmentMVPInteractor
import com.example.shopsavvycommvp.ui.helpcentre.view.HelpCentreFragmentMVPView
import com.example.shopsavvycommvp.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HelpCentreFragmentPresenter @Inject internal constructor(
    interactor: HelpCentreFragmentMVPInteractor,
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<HelpCentreFragmentMVPView, HelpCentreFragmentMVPInteractor>(
    interactor,
    schedulerProvider,
    disposable
), HelpCentreFragmentMVPPresenter {
}