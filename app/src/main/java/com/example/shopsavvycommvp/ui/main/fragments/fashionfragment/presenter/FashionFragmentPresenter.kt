package com.example.shopsavvycommvp.ui.main.fragments.fashionfragment.presenter

import com.example.shopsavvycommvp.ui.base.presenter.BasePresenter
import com.example.shopsavvycommvp.ui.main.fragments.fashionfragment.interactor.FashionFragmentMVPInteractor
import com.example.shopsavvycommvp.ui.main.fragments.fashionfragment.view.FashionFragmentMVPView

import com.example.shopsavvycommvp.ui.main.fragments.userfragment.view.UserFragmentMVPView
import com.example.shopsavvycommvp.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class FashionFragmentPresenter @Inject constructor(
    interactor: FashionFragmentMVPInteractor,
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) :
    BasePresenter<FashionFragmentMVPView, FashionFragmentMVPInteractor>(
        interactor,
        schedulerProvider,
        disposable
    ), FashionFragmentMVPPresenter {

}