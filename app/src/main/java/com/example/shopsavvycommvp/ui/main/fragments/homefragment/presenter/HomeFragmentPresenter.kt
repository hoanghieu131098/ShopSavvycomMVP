package com.example.shopsavvycommvp.ui.main.fragments.homefragment.presenter


import android.util.Log
import com.example.shopsavvycommvp.data.network.request.Order
import com.example.shopsavvycommvp.data.network.response.Category
import com.example.shopsavvycommvp.data.network.response.Product
import com.example.shopsavvycommvp.ui.base.presenter.BasePresenter
import com.example.shopsavvycommvp.ui.main.fragments.homefragment.interactor.HomeFragmentMVPInteractor
import com.example.shopsavvycommvp.ui.main.fragments.homefragment.view.HomeFragmentMVPView
import com.example.shopsavvycommvp.util.SchedulerProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class HomeFragmentPresenter @Inject internal constructor(
    interactor: HomeFragmentMVPInteractor,
    schdulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<HomeFragmentMVPView, HomeFragmentMVPInteractor>(
    interactor,
    schdulerProvider,
    disposable
), HomeFragmentMVPPresenter {

    override fun onAttach(view: HomeFragmentMVPView?) {
        super.onAttach(view)
        getToTalOrder()
    }

    override fun setSearchProduct(q: String) {
        getView()?.showProgress()
        interactor?.let {
            compositeDisposable.add(
                it.searchProduct(q)
                    .compose(schedulerProvider.ioToMainObservableScheduler())
                    .subscribe({ response ->
                        getView()?.let {
                            it.hideProgress()
                            getView()?.onReponseSearchProductSuccess(response as ArrayList<Product>)
                        }
                    }, { error -> getView()?.handleThrowableError(error) })
            )
        }
    }

    override fun getToTalOrder() {
        getView()?.showProgress()
        interactor?.let { interactor ->
            interactor.getFirebase()
                .reference
                .child("Order")
                .child(interactor.getAccount().currentUser!!.uid)
                .addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        getView()?.hideProgress()
                        getView()?.onReponseTaTolOrderFailed("Error Load Total Order")
                    }

                    override fun onDataChange(data: DataSnapshot) {
                        var total = 0
                        for (i in data.children) {
                            val order = i.getValue(Order::class.java)
                            if (!order!!.active) {
                                total++
                            }
                        }
                        Log.d("TotalOrder", "" + total)
                        getView()?.hideProgress()
                        getView()?.onReponseTaTolOrderSuccess(total)
                    }
                })
        }
    }

    override fun getCategory() {
        getView()?.showProgress()
        interactor?.let {
            compositeDisposable.add(
                it.getcategoryAll()
                    .compose(schedulerProvider.ioToMainObservableScheduler())
                    .subscribe({ response ->
                        getView()?.let {
                            if (getView() == null) return@subscribe
                            it.hideProgress()
                            if (response == null) {
                                getView()?.getCategoryAllFailed("Error")
                                return@subscribe
                            }
                            getView()?.getCategoryAllSuccess(response)
                        }
                    }, { error -> getView()?.handleThrowableError(error) })
            )
        }
    }

    override fun getKeyProductAll(key: String) {
        getView()?.showProgress()
        interactor?.let {
            compositeDisposable.add(
                it.getproductAll(key)
                    .compose(schedulerProvider.ioToMainObservableScheduler())
                    .subscribe({ response ->
                        getView()?.let {
                            if (getView() == null) return@subscribe
                            it.hideProgress()
                            //error
                            if (response == null) {
                                getView()?.getProductAllFailed("Error")
                                return@subscribe
                            }
                            getView()?.getProductAllSuccess(response)
                        }
                    }, { error -> getView()?.handleThrowableError(error) })
            )
        }

    }

}