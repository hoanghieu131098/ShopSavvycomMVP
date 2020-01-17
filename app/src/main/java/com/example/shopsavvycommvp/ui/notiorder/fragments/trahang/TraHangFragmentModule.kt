package com.example.shopsavvycommvp.ui.notiorder.fragments.trahang

import com.example.shopsavvycommvp.ui.notiorder.fragments.trahang.interactor.TraHangInteractor
import com.example.shopsavvycommvp.ui.notiorder.fragments.trahang.interactor.TraHangMVPInteractor
import com.example.shopsavvycommvp.ui.notiorder.fragments.trahang.presenter.TraHangMVPPresenter
import com.example.shopsavvycommvp.ui.notiorder.fragments.trahang.presenter.TraHangPresenter
import dagger.Module
import dagger.Provides

@Module
class TraHangFragmentModule {
    @Provides
    fun provideTraHangInteractor(interactor: TraHangInteractor): TraHangMVPInteractor = interactor
    @Provides
    fun provideTraHangPresenter(presenter: TraHangPresenter): TraHangMVPPresenter = presenter
}