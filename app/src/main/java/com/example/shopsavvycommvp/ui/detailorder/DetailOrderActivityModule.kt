package com.example.shopsavvycommvp.ui.detailorder

import com.example.shopsavvycommvp.ui.detailorder.interactor.DetailOrderInteractor
import com.example.shopsavvycommvp.ui.detailorder.interactor.DetailOrderMVPInteractor
import com.example.shopsavvycommvp.ui.detailorder.presenter.DetailOrderMVPPresenter
import com.example.shopsavvycommvp.ui.detailorder.presenter.DetailOrderPresenter
import com.example.shopsavvycommvp.ui.detailorder.view.DetailOrderMVPView
import dagger.Module
import dagger.Provides

@Module
class DetailOrderActivityModule {
    @Provides
    fun provideDetailOrderInteractor(interactor: DetailOrderInteractor): DetailOrderMVPInteractor =
        interactor

    @Provides
    fun provideDetailOrderPresenter(presenter: DetailOrderPresenter<DetailOrderMVPView, DetailOrderMVPInteractor>)
            : DetailOrderMVPPresenter<DetailOrderMVPView, DetailOrderMVPInteractor> = presenter
}