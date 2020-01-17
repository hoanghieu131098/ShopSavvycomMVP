package com.example.shopsavvycommvp.ui.notiorder.fragments.dahuy

import com.example.shopsavvycommvp.ui.notiorder.fragments.dahuy.interactor.DaHuyInteractor
import com.example.shopsavvycommvp.ui.notiorder.fragments.dahuy.interactor.DaHuyMVPInteractor
import com.example.shopsavvycommvp.ui.notiorder.fragments.dahuy.presenter.DaHuyMVPPresenter
import com.example.shopsavvycommvp.ui.notiorder.fragments.dahuy.presenter.DaHuyPresenter
import dagger.Module
import dagger.Provides

@Module
class DaHuyFragmentModule {
    @Provides
    fun provideDaHuyInteractor(interactor: DaHuyInteractor): DaHuyMVPInteractor = interactor
    @Provides
    fun provideDaHuyPresenter(presenter: DaHuyPresenter): DaHuyMVPPresenter = presenter
}