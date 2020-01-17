package com.example.shopsavvycommvp.ui.detailproduct.activities

import com.example.shopsavvycommvp.ui.detailproduct.activities.interactor.DetailProductInteractor
import com.example.shopsavvycommvp.ui.detailproduct.activities.interactor.DetailProductMVPInteractor
import com.example.shopsavvycommvp.ui.detailproduct.activities.presenter.DetailProductMVPPresenter
import com.example.shopsavvycommvp.ui.detailproduct.activities.presenter.DetailProductPresenter
import com.example.shopsavvycommvp.ui.detailproduct.activities.view.DetailProductMVPView
import dagger.Module
import dagger.Provides

@Module
class DetailProductModule {
    @Provides
    internal fun provideDetailProductInteractor(interactor: DetailProductInteractor): DetailProductMVPInteractor = interactor

    @Provides
    internal  fun provideDetailProductPresenter(presenter: DetailProductPresenter<DetailProductMVPView,DetailProductMVPInteractor>):
            DetailProductMVPPresenter<DetailProductMVPView,DetailProductMVPInteractor> = presenter
}