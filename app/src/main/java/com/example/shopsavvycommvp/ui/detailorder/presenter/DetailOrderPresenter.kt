package com.example.shopsavvycommvp.ui.detailorder.presenter

import com.example.shopsavvycommvp.data.network.request.Order
import com.example.shopsavvycommvp.ui.base.presenter.BasePresenter
import com.example.shopsavvycommvp.ui.detailorder.interactor.DetailOrderMVPInteractor
import com.example.shopsavvycommvp.ui.detailorder.view.DetailOrderMVPView
import com.example.shopsavvycommvp.util.SchedulerProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DetailOrderPresenter<V : DetailOrderMVPView, I : DetailOrderMVPInteractor> @Inject internal constructor(
    interactor: I,
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V, I>(interactor, schedulerProvider, disposable), DetailOrderMVPPresenter<V, I> {
    override fun setIdOrderLoadDetail(id: String) {
        getView()?.showProgress()
        interactor?.let { interactor ->
            interactor.getFirebase()
                .reference
                .child("Order")
                .child(interactor.getAccount().currentUser!!.uid)
                .addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        getView()?.hideProgress()
                    }

                    override fun onDataChange(data: DataSnapshot) {
                        for (i in data.children) {
                            val order = i.getValue(Order::class.java)
                            if (order?.id.equals(id)) {
                                order?.let { getView()?.onReponseDetailOrder(it) }
                                break
                            }
                        }
                        getView()?.hideProgress()
                    }
                })
        }
    }
}