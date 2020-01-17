package com.example.shopsavvycommvp.ui.detailcategory

import com.example.shopsavvycommvp.ui.detailcategory.interactor.DetailCategoryInteractor
import com.example.shopsavvycommvp.ui.detailcategory.interactor.DetailCategoryMVPInteractor
import com.example.shopsavvycommvp.ui.detailcategory.presenter.DetailCategoryMVPPresenter
import com.example.shopsavvycommvp.ui.detailcategory.presenter.DetailCategoryPresenter
import com.example.shopsavvycommvp.ui.detailcategory.view.DetailCategoryMVPView
import dagger.Module
import dagger.Provides

@Module
class DetailCategoryModule {
 @Provides
 internal fun provideDetailCategoryInteractor(interactor: DetailCategoryInteractor): DetailCategoryMVPInteractor = interactor
    @Provides
    internal fun provideDetailCategoryPresenter(presenter: DetailCategoryPresenter<DetailCategoryMVPView,DetailCategoryMVPInteractor>)
    : DetailCategoryMVPPresenter<DetailCategoryMVPView,DetailCategoryMVPInteractor> = presenter

}