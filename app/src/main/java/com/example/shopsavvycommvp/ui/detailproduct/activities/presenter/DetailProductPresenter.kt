package com.example.shopsavvycommvp.ui.detailproduct.activities.presenter

import com.example.shopsavvycommvp.data.network.request.Cart
import com.example.shopsavvycommvp.ui.base.presenter.BasePresenter
import com.example.shopsavvycommvp.ui.detailproduct.activities.interactor.DetailProductMVPInteractor
import com.example.shopsavvycommvp.ui.detailproduct.activities.view.DetailProductMVPView
import com.example.shopsavvycommvp.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DetailProductPresenter<V : DetailProductMVPView,I :DetailProductMVPInteractor > @Inject internal
constructor(interactor: I,schedulerProvider: SchedulerProvider,disposable: CompositeDisposable):
BasePresenter<V,I>(interactor,schedulerProvider,disposable),DetailProductMVPPresenter<V,I>{
    override fun updateCart(cart: Cart) {
        getView()?.showProgress()
        interactor?.let { interactor ->
            interactor.getFirebase()
                .reference
                .child("Cart")
                .child(interactor.getAccount().currentUser!!.uid)
                .child(cart.product!!.id.toString())
                .setValue(cart)
                .addOnCompleteListener {
                    getView()?.hideProgress()
                    if(it.isSuccessful){
                        getView()?.onResponse("Success")
                    }else{
                        getView()?.onResponse("Error")
                    }
                }
        }
    }
}