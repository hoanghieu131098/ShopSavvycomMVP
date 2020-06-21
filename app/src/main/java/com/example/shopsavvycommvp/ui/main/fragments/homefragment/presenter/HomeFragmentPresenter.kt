package com.example.shopsavvycommvp.ui.main.fragments.homefragment.presenter


import android.util.Log
import com.example.shopsavvycommvp.data.network.request.Order
import com.example.shopsavvycommvp.data.network.response.Category
import com.example.shopsavvycommvp.data.network.response.Product
import com.example.shopsavvycommvp.ui.base.presenter.BasePresenter
import com.example.shopsavvycommvp.ui.main.fragments.homefragment.interactor.HomeFragmentMVPInteractor
import com.example.shopsavvycommvp.ui.main.fragments.homefragment.view.HomeFragmentMVPView
import com.example.shopsavvycommvp.util.AppConstants
import com.example.shopsavvycommvp.util.SchedulerProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import java.util.logging.Handler
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
        interactor?.getFirebase()?.reference?.child(AppConstants.Category.CATEGORY)
            ?.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    getView()?.hideProgress()
                    getView()?.getCategoryAllFailed(p0.message)
                }

                override fun onDataChange(p0: DataSnapshot) {
                    getView()?.hideProgress()
                    val listCategory = arrayListOf<Category>()
                    for (i in p0.children) {
                        val category = i.getValue(Category::class.java)
                        category?.let { listCategory.add(it) }
                    }
                    getView()?.getCategoryAllSuccess(listCategory)
                }
            })
    }


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



}