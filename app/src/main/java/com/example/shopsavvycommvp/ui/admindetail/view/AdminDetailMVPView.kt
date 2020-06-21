package com.example.shopsavvycommvp.ui.admindetail.view

import com.example.shopsavvycommvp.data.network.response.Category
import com.example.shopsavvycommvp.ui.base.view.MVPView

interface AdminDetailMVPView: MVPView {
    fun getAllCategorySuccess(data: ArrayList<Category>)
    fun getAllCategoryFail(msg: String)

    fun haveError(mes: String)
    fun updateProductSuccess()
}