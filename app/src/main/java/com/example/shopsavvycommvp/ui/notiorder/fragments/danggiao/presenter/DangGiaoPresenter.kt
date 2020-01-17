package com.example.shopsavvycommvp.ui.notiorder.fragments.danggiao.presenter

import com.example.shopsavvycommvp.data.network.request.Order
import com.example.shopsavvycommvp.ui.base.presenter.BasePresenter
import com.example.shopsavvycommvp.ui.notiorder.fragments.danggiao.interactor.DangGiaoMVPInteractor
import com.example.shopsavvycommvp.ui.notiorder.fragments.danggiao.view.DangGiaoMVPView
import com.example.shopsavvycommvp.util.SchedulerProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DangGiaoPresenter @Inject internal  constructor(
    interactor: DangGiaoMVPInteractor,
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
): BasePresenter<DangGiaoMVPView,DangGiaoMVPInteractor>(interactor,schedulerProvider,disposable),DangGiaoMVPPresenter{
    override fun doUpdateActive(id: String) {
        getView()?.showProgress()
        interactor?.let { interactor ->
            interactor.getFirebase()
                .reference
                .child("Order")
                .child(interactor.getAccount().currentUser!!.uid)
                .child(id)
                .child("active")
                .setValue(true)
                .addOnCompleteListener {
                    getView()?.hideProgress()
                    if(it.isSuccessful){
                        getView()?.onReponseUpdateActive("Success")
                    }else{
                        getView()?.onReponseUpdateActive("Failed")
                    }
                }
        }
    }

    override fun onAttach(view: DangGiaoMVPView?) {
        super.onAttach(view)
        doloadOrder()
    }
    private var mdata: ArrayList<Order>? = arrayListOf()
    override fun doloadOrder() {
        getView()?.showProgress()
        interactor?.let { interactor ->
            interactor.getFirebase()
                .reference
                .child("Order")
                .child(interactor.getAccount().currentUser!!.uid)
                .addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        getView()?.hideProgress()
                        getView()?.onReponseFailed("load order error")
                    }
                    override fun onDataChange(data: DataSnapshot) {
                        data.let {
                            for(i in data.children){
                                val order = i.getValue(Order::class.java)
                                mdata?.add(order!!)
                            }
                            getView()?.hideProgress()
                            getView()?.onReponseSuccess(mdata!!)
                            mdata = null
                            mdata = arrayListOf()
                        }
                    }
                })
        }
    }
}