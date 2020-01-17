package com.example.shopsavvycommvp.ui.notiorder.fragments.dagiao

import com.example.shopsavvycommvp.ui.notiorder.fragments.dagiao.interactor.DaGiaoInteractor
import com.example.shopsavvycommvp.ui.notiorder.fragments.dagiao.interactor.DaGiaoMVPInteractor
import com.example.shopsavvycommvp.ui.notiorder.fragments.dagiao.presenter.DaGiaoMVPPresenter
import com.example.shopsavvycommvp.ui.notiorder.fragments.dagiao.presenter.DaGiaoPresenter
import dagger.Module
import dagger.Provides

@Module
class DaGiaoFragmentModule {
    @Provides
    fun provideDaGiaoInteractor(interactor: DaGiaoInteractor): DaGiaoMVPInteractor = interactor
    @Provides
    fun provideDaGiaoPresenter(presenter: DaGiaoPresenter): DaGiaoMVPPresenter = presenter
}