package com.example.shopsavvycommvp.ui.admin.view

import com.example.shopsavvycommvp.data.network.response.Product
import com.example.shopsavvycommvp.ui.base.view.MVPView

interface AdminActivityMVPView: MVPView {
    fun getProductAllFailed(msg: String)
    fun getProductAllSuccess(data : ArrayList<Product>)

    fun removeProductFail(msg: String)
    fun removeProductSuccess()
}