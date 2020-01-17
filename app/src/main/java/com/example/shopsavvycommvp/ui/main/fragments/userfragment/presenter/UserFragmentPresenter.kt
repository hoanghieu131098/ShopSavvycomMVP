package com.example.shopsavvycommvp.ui.main.fragments.userfragment.presenter

import com.example.shopsavvycommvp.data.network.request.Cart
import com.example.shopsavvycommvp.ui.base.presenter.BasePresenter
import com.example.shopsavvycommvp.ui.main.fragments.userfragment.interactor.UserFragmentMVPInteractor
import com.example.shopsavvycommvp.ui.main.fragments.userfragment.view.UserFragmentMVPView
import com.example.shopsavvycommvp.util.SchedulerProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class UserFragmentPresenter @Inject constructor(
    interactor: UserFragmentMVPInteractor,
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) :
    BasePresenter<UserFragmentMVPView, UserFragmentMVPInteractor>(
        interactor,
        schedulerProvider,
        disposable
    ), UserFragmentMVPPresenter {

    override fun onAttach(view: UserFragmentMVPView?) {
        super.onAttach(view)
        getNumberCart()
    }

    private fun getNumberCart() {
           getView()?.showProgress()
        interactor?.let {interactor ->
            interactor.getFirebase()
                .reference
                .child("Cart")
                .child(interactor.getAccount().currentUser!!.uid)
                .addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        getView()?.hideProgress()
                    }

                    override fun onDataChange(data: DataSnapshot) {
                        var totalItemCart: Int = 0
                        data.let {
                            for (i in it.children) {
                                val cartCart = i.getValue(Cart::class.java)
                                totalItemCart = totalItemCart +  cartCart!!.total
                            }
                            getView()?.numberItemCart(totalItemCart)
                            getView()?.hideProgress()

                        }
                    }
                })

        }
    }
}