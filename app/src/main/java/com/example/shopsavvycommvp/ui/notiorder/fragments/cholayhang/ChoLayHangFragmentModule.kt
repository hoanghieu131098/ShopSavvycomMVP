package com.example.shopsavvycommvp.ui.notiorder.fragments.cholayhang

import com.example.shopsavvycommvp.ui.notiorder.fragments.cholayhang.interactor.ChoLayHangInteractor
import com.example.shopsavvycommvp.ui.notiorder.fragments.cholayhang.interactor.ChoLayHangMVPInteractor
import com.example.shopsavvycommvp.ui.notiorder.fragments.cholayhang.presenter.ChoLayHangMVPPresenter
import com.example.shopsavvycommvp.ui.notiorder.fragments.cholayhang.presenter.ChoLayHangPresenter
import dagger.Module
import dagger.Provides

@Module
class ChoLayHangFragmentModule {
    @Provides
    fun provideChoLayHangInteractor(interactor: ChoLayHangInteractor): ChoLayHangMVPInteractor = interactor
    @Provides
    fun provideChoLayHangPresenter(presenter: ChoLayHangPresenter): ChoLayHangMVPPresenter = presenter
}