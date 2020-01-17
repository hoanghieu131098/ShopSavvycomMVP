package com.example.shopsavvycommvp.ui.main.fragments.cartfragment.presenter

import com.example.shopsavvycommvp.data.network.request.Cart
import com.example.shopsavvycommvp.ui.base.presenter.BasePresenter
import com.example.shopsavvycommvp.ui.base.view.BaseAdapter
import com.example.shopsavvycommvp.ui.main.fragments.cartfragment.interactor.CartFragmentMVPInteractor
import com.example.shopsavvycommvp.ui.main.fragments.cartfragment.presenter.CartFragmentMVPPresenter
import com.example.shopsavvycommvp.ui.main.fragments.cartfragment.view.CartFragmentMVPView
import com.example.shopsavvycommvp.ui.main.fragments.userfragment.view.UserFragmentMVPView
import com.example.shopsavvycommvp.util.SchedulerProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class CartFragmentPresenter @Inject constructor(
    interactor: CartFragmentMVPInteractor,
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) :
    BasePresenter<CartFragmentMVPView, CartFragmentMVPInteractor>(
        interactor,
        schedulerProvider,
        disposable
    ), CartFragmentMVPPresenter {
    override fun getToTalProductItem(total: Int, id: Int) {
        getView()?.showProgress()
        interactor?.let { interactor ->
            interactor.getFirebase()
                .reference
                .child("Cart")
                .child(interactor.getAccount().currentUser!!.uid)
                .child(id.toString())
                .child("total")
                .setValue(total)
                .addOnCompleteListener {
                    getView()?.hideProgress()
                    if (it.isSuccessful) {
                        getView()?.onResponseUpdatetotalProductCart("update total success")
                    } else {
                        getView()?.onResponseUpdatetotalProductCart("update total failed")
                    }
                }
        }
    }

    override fun getClickDeleteItemCart(id: Int) {
        getView()?.showProgress()
        interactor?.let { interactor ->
            interactor.getFirebase()
                .reference
                .child("Cart")
                .child(interactor.getAccount().currentUser!!.uid)
                .child(id.toString())
                .removeValue()
                .addOnCompleteListener {
                    getView()?.hideProgress()
                    if (it.isSuccessful) {
                        getView()?.onResponseRemoveProductCart("remove item success")
                    } else {
                        getView()?.onResponseRemoveProductCart("remove item failed")
                    }
                }
        }
    }

    override fun onAttach(view: CartFragmentMVPView?) {
        super.onAttach(view)
        getCart()
    }

    private var mList: ArrayList<Cart>? = arrayListOf()
    private fun getCart() {
        getView()?.showProgress()
        interactor?.let { interactor ->
            interactor.getFirebase()
                .reference
                .child("Cart")
                .child(interactor.getAccount().currentUser!!.uid)
                .addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        getView()?.hideProgress()
                        getView()?.onFailed(p0.toString())
                    }

                    override fun onDataChange(data: DataSnapshot) {
                        data.let {
                            for (i in it.children) {
                                val cartCart = i.getValue(Cart::class.java)
                                mList!!.add(cartCart!!)
                            }
                            getView()?.onResponse(mList!!)
                            getView()?.hideProgress()
                            mList = null
                            mList = arrayListOf()

                        }
                    }
                })
        }
    }

}