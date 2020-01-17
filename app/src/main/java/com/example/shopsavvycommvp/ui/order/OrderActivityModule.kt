package com.example.shopsavvycommvp.ui.order

import com.example.shopsavvycommvp.ui.order.interactor.OrderInteractor
import com.example.shopsavvycommvp.ui.order.interactor.OrderMVPInteractor
import com.example.shopsavvycommvp.ui.order.presenter.OrderMVPPresenter
import com.example.shopsavvycommvp.ui.order.presenter.OrderPresenter
import com.example.shopsavvycommvp.ui.order.view.OrderMVPView
import dagger.Module
import dagger.Provides

@Module
class OrderActivityModule {
    @Provides
    fun provideOrderInteractor(interactor: OrderInteractor): OrderMVPInteractor = interactor
    @Provides
    fun providePresenter(presenter: OrderPresenter<OrderMVPView,OrderMVPInteractor>): OrderMVPPresenter<OrderMVPView,OrderMVPInteractor> = presenter

}