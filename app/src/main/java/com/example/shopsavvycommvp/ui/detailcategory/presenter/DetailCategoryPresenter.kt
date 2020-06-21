package com.example.shopsavvycommvp.ui.detailcategory.presenter

import com.example.shopsavvycommvp.data.network.response.Product
import com.example.shopsavvycommvp.ui.base.presenter.BasePresenter
import com.example.shopsavvycommvp.ui.detailcategory.interactor.DetailCategoryMVPInteractor
import com.example.shopsavvycommvp.ui.detailcategory.view.DetailCategoryMVPView
import com.example.shopsavvycommvp.util.AppConstants
import com.example.shopsavvycommvp.util.SchedulerProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
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
    override fun getProductFollowCategory(key: Int) {
        getView()?.showProgress()
        interactor?.getFirebase()?.reference?.child(AppConstants.Product.PRODUCT)
            ?.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    getView()?.hideProgress()
                    getView()?.getProductAllFailed(p0.message)
                }

                override fun onDataChange(p0: DataSnapshot) {
                    getView()?.hideProgress()
                    val listProduct = arrayListOf<Product>()
                    for (i in p0.children) {
                        val product = i.getValue(Product::class.java)
                        if (product?.idCategory == key) {
                            product.let { listProduct.add(it) }
                        }
                    }
                    getView()?.getProductAllSuccess(listProduct)
                }
            })
    }

}