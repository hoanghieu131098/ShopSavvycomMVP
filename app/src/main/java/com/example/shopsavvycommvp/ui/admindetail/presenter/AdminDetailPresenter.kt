package com.example.shopsavvycommvp.ui.admindetail.presenter

import android.net.Uri
import com.example.shopsavvycommvp.data.network.response.Category
import com.example.shopsavvycommvp.data.network.response.Product
import com.example.shopsavvycommvp.ui.admin.interactor.AdminActivityMVPInteractor
import com.example.shopsavvycommvp.ui.admin.presenter.AdminActivityMVPPresenter
import com.example.shopsavvycommvp.ui.admin.view.AdminActivityMVPView
import com.example.shopsavvycommvp.ui.admindetail.interactor.AdminDetailMVPInteractor
import com.example.shopsavvycommvp.ui.admindetail.view.AdminDetailMVPView
import com.example.shopsavvycommvp.ui.base.presenter.BasePresenter
import com.example.shopsavvycommvp.util.AppConstants
import com.example.shopsavvycommvp.util.SchedulerProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import javax.inject.Inject

class AdminDetailPresenter<V : AdminDetailMVPView, I : AdminDetailMVPInteractor> @Inject internal constructor(
    interactor: I,
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) :
    BasePresenter<V, I>(interactor, schedulerProvider, disposable),
    AdminDetailMVPPresenter<V, I> {
    override fun getAllCategory() {
        getView()?.showProgress()
        interactor?.getFirebase()?.reference?.child(AppConstants.Category.CATEGORY)
            ?.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    getView()?.hideProgress()
                    getView()?.getAllCategoryFail(p0.message)
                }

                override fun onDataChange(p0: DataSnapshot) {
                    getView()?.hideProgress()
                    val listProduct = arrayListOf<Category>()
                    for (i in p0.children) {
                        val category = i.getValue(Category::class.java)
                        category?.let { listProduct.add(it) }
                    }
                    getView()?.getAllCategorySuccess(listProduct)
                }
            })
    }

    private var storageRef = interactor.getFirebaseStorage().getReferenceFromUrl(AppConstants.LINK_STORAGE_FIREBASE)
    override fun updateProduct(imageUri: Uri?, product: Product) {
        getView()?.showProgress()
        if (imageUri != null) {
            val mountainRef =
                storageRef.child(AppConstants.Product.PRODUCT).child(product.id.toString())
                    .child("product${Calendar.getInstance().timeInMillis}.png")
            mountainRef.putFile(imageUri)
                .addOnSuccessListener { taskSnapshot ->
                    val downloadUri = taskSnapshot.storage.downloadUrl
                    downloadUri.addOnSuccessListener {
                        if (product.dataimage == null) {
                            val list = arrayListOf<String>()
                            list.add(downloadUri.result.toString())
                            product.dataimage = list
                        } else {
                            product.dataimage?.set(0, downloadUri.result.toString())
                        }
                        updateToDB(product)
                    }

                }
                .addOnFailureListener {
                    getView()?.hideProgress()
                    it.message?.let { it1 -> getView()?.haveError(it1) }
                }
        } else {
            updateToDB(product)
        }
    }

    private fun updateToDB(product: Product) {
        interactor?.getFirebase()?.reference?.child(AppConstants.Product.PRODUCT)
            ?.child(product.id.toString())?.setValue(product)
            ?.addOnCompleteListener {
                getView()?.hideProgress()
                getView()?.updateProductSuccess()
            }
            ?.addOnFailureListener {
                getView()?.hideProgress()
                it.message?.let { it1 -> getView()?.haveError(it1) }
            }
    }

}