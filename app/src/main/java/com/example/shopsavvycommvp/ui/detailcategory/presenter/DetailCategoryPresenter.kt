package com.example.shopsavvycommvp.ui.detailcategory.presenter

import com.example.shopsavvycommvp.data.network.response.Product
import com.example.shopsavvycommvp.ui.base.presenter.BasePresenter
import com.example.shopsavvycommvp.ui.detailcategory.interactor.DetailCategoryMVPInteractor
import com.example.shopsavvycommvp.ui.detailcategory.view.DetailCategoryMVPView
import com.example.shopsavvycommvp.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DetailCategoryPresenter<V : DetailCategoryMVPView, I : DetailCategoryMVPInteractor> @Inject internal constructor(
    interactor: I,
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) :
    BasePresenter<V, I>(interactor, schedulerProvider, disposable),
    DetailCategoryMVPPresenter<V, I> {
    lateinit var data: List<Product>
    override fun getKeyProductAll(key: String) {
        getView()?.showProgress()
        interactor?.let {
            compositeDisposable.add(it.getproductAll(key)
                .compose(schedulerProvider.ioToMainObservableScheduler())
                .subscribe({ response ->
                    getView()?.let {
                        if (getView() == null) return@subscribe
                        it.hideProgress()
                        if (response == null) {
                            getView()?.getProductAllFailed(com.example.shopsavvycommvp.R.string.not_item.toString())
                            return@subscribe
                        }
                        getView()?.getProductAllSuccess(response)
                    }
                }, { error ->
                    getView()?.handleThrowableError(error)
                    data = arrayListOf()
                    getView()?.getProductAllSuccess(data)
                })
            )
        }
    }

}