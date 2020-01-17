package com.example.shopsavvycommvp.ui.detailcategory.interactor

import com.example.shopsavvycommvp.data.network.response.Product
import com.example.shopsavvycommvp.ui.base.interactor.MVPInteractor
import io.reactivex.Observable


interface DetailCategoryMVPInteractor: MVPInteractor {
    fun getproductAll(key: String): Observable<List<Product>>

}