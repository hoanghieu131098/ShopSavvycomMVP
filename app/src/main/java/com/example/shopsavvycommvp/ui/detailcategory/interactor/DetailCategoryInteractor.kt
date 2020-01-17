package com.example.shopsavvycommvp.ui.detailcategory.interactor

import com.example.shopsavvycommvp.data.network.AppApi
import com.example.shopsavvycommvp.data.network.response.Product
import com.example.shopsavvycommvp.ui.base.interactor.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject


class DetailCategoryInteractor @Inject constructor(apiHelper: AppApi): BaseInteractor(apiHelper),DetailCategoryMVPInteractor {
    override fun getproductAll(key: String): Observable<List<Product>> {
        return appApi.getProduct(key)
    }

}