package com.example.shopsavvycommvp.ui.order.presenter

import android.util.Log
import com.example.shopsavvycommvp.data.network.request.Order
import com.example.shopsavvycommvp.ui.base.presenter.BasePresenter
import com.example.shopsavvycommvp.ui.order.interactor.OrderInteractor
import com.example.shopsavvycommvp.ui.order.interactor.OrderMVPInteractor
import com.example.shopsavvycommvp.ui.order.view.OrderMVPView
import com.example.shopsavvycommvp.util.SchedulerProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class OrderPresenter<V : OrderMVPView, I : OrderMVPInteractor> @Inject internal constructor(
    interactor: I,
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V, I>(interactor, schedulerProvider, disposable), OrderMVPPresenter<V, I> {

    private var mDatarerence: DatabaseReference? = null
    override fun getOrder(order: Order) {
        getView()?.showProgress()
        interactor?.let { interactor ->
            mDatarerence = interactor.getFirebase()
                .reference
                .child("Order")
                .child(interactor.getAccount().currentUser!!.uid)
                .push()

            val keyId: String? = mDatarerence?.key
            order.id = keyId
            mDatarerence?.setValue(order)?.addOnCompleteListener {
                getView()?.hideProgress()
                if (it.isSuccessful) {
                    interactor.getFirebase()
                        .reference
                        .child("Cart")
                        .child(interactor.getAccount().currentUser!!.uid)
                        .removeValue()
                        .addOnCompleteListener {
                            getView()?.hideProgress()
                            if (it.isSuccessful) {
                                getView()?.orderSuccess("Success")
                            } else {
                                getView()?.orderFailed("Failed")
                            }
                        }
                } else {
                    getView()?.orderFailed("Failed")
                }
            }

        }

    }

    override fun checkInforUserOrder(userID: String) {
        getView()?.showProgress()
        interactor?.getFirebase()?.reference?.child("Order")?.child(userID)
            ?.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    getView()?.hideProgress()
                    getView()?.checkInforFail(p0.message)
                }

                override fun onDataChange(p0: DataSnapshot) {
                    getView()?.hideProgress()
                    val list = arrayListOf<Order>()
                    for (i in p0.children) {
                        val order = i.getValue(Order::class.java)
                        order?.let { list.add(it) }
                    }
                    if (list.size != 0) {
                        getView()?.checkInforSuccess(list[list.size - 1])
                    }
                }
            })
    }

}
