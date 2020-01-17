package com.example.shopsavvycommvp.ui.base.presenter

import com.example.shopsavvycommvp.ui.base.interactor.MVPInteractor
import com.example.shopsavvycommvp.ui.base.view.MVPView
import io.reactivex.subjects.PublishSubject

 interface MVPPresenter<V : MVPView, I : MVPInteractor> {

    fun onAttach(view: V?)
    fun onDetach()
    fun getView(): V?
    fun onDestroy()
    fun preDestroy(): PublishSubject<Boolean>
}