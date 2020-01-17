package com.example.shopsavvycommvp.ui.base.presenter

import com.example.shopsavvycommvp.ui.base.interactor.MVPInteractor
import com.example.shopsavvycommvp.ui.base.view.MVPView
import com.example.shopsavvycommvp.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

abstract class BasePresenter<V : MVPView, I : MVPInteractor> internal constructor(
    protected var interactor: I?,
    protected val schedulerProvider: SchedulerProvider,
    protected val compositeDisposable: CompositeDisposable
) : MVPPresenter<V, I> {


    private var view: V? = null
    private val isViewAttached: Boolean get() = view != null
    var preDestroy = PublishSubject.create<Boolean>()
    override fun onAttach(view: V?) {
        this.view = view
    }

    override fun getView(): V? = view

    override fun onDetach() {
        compositeDisposable.dispose()
        view = null
        interactor = null
    }

    override fun onDestroy() {
        preDestroy.onNext(true)
        compositeDisposable.dispose()
        view = null
        interactor = null
    }

    override fun preDestroy(): PublishSubject<Boolean> {
        return preDestroy
    }


}