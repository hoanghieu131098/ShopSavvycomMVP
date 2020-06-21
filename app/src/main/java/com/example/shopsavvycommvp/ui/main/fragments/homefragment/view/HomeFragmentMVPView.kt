package com.example.shopsavvycommvp.ui.main.fragments.homefragment.view

import com.example.shopsavvycommvp.data.network.response.Category
import com.example.shopsavvycommvp.data.network.response.Product
import com.example.shopsavvycommvp.ui.base.view.MVPView


interface HomeFragmentMVPView : MVPView{
    fun getProductAllFailed(msg: String)
    fun getProductAllSuccess(data : ArrayList<Product>)

    fun getCategoryAllFailed(msg: String)
    fun getCategoryAllSuccess(data : List<Category>)

    fun onReponseTaTolOrderFailed(msg: String)
    fun onReponseTaTolOrderSuccess(total: Int)

}