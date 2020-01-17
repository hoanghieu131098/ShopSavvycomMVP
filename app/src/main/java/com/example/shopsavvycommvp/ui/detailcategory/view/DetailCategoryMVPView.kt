package com.example.shopsavvycommvp.ui.detailcategory.view

import com.example.shopsavvycommvp.data.network.response.Product
import com.example.shopsavvycommvp.ui.base.view.MVPView


interface DetailCategoryMVPView  : MVPView{
    fun getProductAllFailed(msg: String)
    fun getProductAllSuccess(data : List<Product>)
}