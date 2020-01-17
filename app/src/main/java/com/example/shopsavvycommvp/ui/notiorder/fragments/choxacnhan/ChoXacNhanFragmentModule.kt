package com.example.shopsavvycommvp.ui.notiorder.fragments.choxacnhan

import com.example.shopsavvycommvp.ui.notiorder.fragments.choxacnhan.interactor.ChoXacNhanInteractor
import com.example.shopsavvycommvp.ui.notiorder.fragments.choxacnhan.interactor.ChoXacNhanMVPInteractor
import com.example.shopsavvycommvp.ui.notiorder.fragments.choxacnhan.presenter.ChoXacNhanMVPPresenter
import com.example.shopsavvycommvp.ui.notiorder.fragments.choxacnhan.presenter.ChoXacNhanPresenter
import dagger.Module
import dagger.Provides

@Module
class ChoXacNhanFragmentModule {
    @Provides
    fun provideChoXacNhanInteractor(interactor: ChoXacNhanInteractor): ChoXacNhanMVPInteractor = interactor
    @Provides
    fun provideChoXacNhanPresenter(presenter: ChoXacNhanPresenter): ChoXacNhanMVPPresenter = presenter
}