package com.example.shopsavvycommvp.ui.detailcategory.presenter

import com.example.shopsavvycommvp.ui.base.presenter.MVPPresenter
import com.example.shopsavvycommvp.ui.detailcategory.interactor.DetailCategoryMVPInteractor
import com.example.shopsavvycommvp.ui.detailcategory.view.DetailCategoryMVPView
import com.example.shopsavvycommvp.ui.detailproduct.activities.view.DetailProductMVPView


interface DetailCategoryMVPPresenter<V: DetailCategoryMVPView,I: DetailCategoryMVPInteractor>: MVPPresenter<V,I>{
    fun getProductFollowCategory(key: Int)
}