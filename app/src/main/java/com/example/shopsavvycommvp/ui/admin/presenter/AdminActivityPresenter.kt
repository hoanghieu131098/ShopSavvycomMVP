package com.example.shopsavvycommvp.ui.admin.presenter

import com.example.shopsavvycommvp.data.network.response.Product
import com.example.shopsavvycommvp.ui.admin.interactor.AdminActivityMVPInteractor
import com.example.shopsavvycommvp.ui.admin.view.AdminActivityMVPView
import com.example.shopsavvycommvp.ui.base.presenter.BasePresenter
import com.example.shopsavvycommvp.util.AppConstants
import com.example.shopsavvycommvp.util.SchedulerProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class AdminActivityPresenter<V : AdminActivityMVPView, I : AdminActivityMVPInteractor> @Inject internal constructor(
    interactor: I,
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) :
    BasePresenter<V, I>(interactor, schedulerProvider, disposable),
    AdminActivityMVPPresenter<V, I> {

    override fun getProductToRcv() {
            getView()?.showProgress()
            interactor?.getFirebase()?.reference?.child(AppConstants.Product.PRODUCT)
                ?.addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        getView()?.hideProgress()
                        getView()?.getProductAllFailed(p0.message)
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        getView()?.hideProgress()
                        val list = arrayListOf<Product>()
                        for (i in p0.children) {
                            val product = i.getValue(Product::class.java)
                            product?.let { list.add(it) }
                        }
                        getView()?.getProductAllSuccess(list)
                    }
                })

    }

    override fun removeProduct(key: Int) {
        getView()?.showProgress()
        interactor?.getFirebase()?.reference?.child(AppConstants.Product.PRODUCT)?.child(key.toString())
            ?.removeValue()
            ?.addOnSuccessListener {
                getView()?.hideProgress()
                getView()?.removeProductSuccess()
            }
            ?.addOnFailureListener {
                getView()?.hideProgress()
                getView()?.removeProductFail(it.message.toString())
            }
    }

}