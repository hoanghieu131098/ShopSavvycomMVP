package com.example.shopsavvycommvp.ui.admindetail.presenter

import android.net.Uri
import com.example.shopsavvycommvp.data.network.response.Product
import com.example.shopsavvycommvp.ui.admin.interactor.AdminActivityMVPInteractor
import com.example.shopsavvycommvp.ui.admin.view.AdminActivityMVPView
import com.example.shopsavvycommvp.ui.admindetail.interactor.AdminDetailMVPInteractor
import com.example.shopsavvycommvp.ui.admindetail.view.AdminDetailMVPView
import com.example.shopsavvycommvp.ui.base.presenter.MVPPresenter

interface AdminDetailMVPPresenter<V: AdminDetailMVPView,I: AdminDetailMVPInteractor>:
    MVPPresenter<V, I> {
    fun getAllCategory()
    fun updateProduct(imageUri: Uri?, product: Product)
}