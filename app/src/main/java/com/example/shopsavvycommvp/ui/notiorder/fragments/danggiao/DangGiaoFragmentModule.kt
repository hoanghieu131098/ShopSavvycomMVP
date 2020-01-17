package com.example.shopsavvycommvp.ui.notiorder.fragments.danggiao

import com.example.shopsavvycommvp.ui.notiorder.fragments.danggiao.interactor.DangGiaoInteractor
import com.example.shopsavvycommvp.ui.notiorder.fragments.danggiao.interactor.DangGiaoMVPInteractor
import com.example.shopsavvycommvp.ui.notiorder.fragments.danggiao.presenter.DangGiaoMVPPresenter
import com.example.shopsavvycommvp.ui.notiorder.fragments.danggiao.presenter.DangGiaoPresenter
import dagger.Module
import dagger.Provides

@Module
class DangGiaoFragmentModule {
    @Provides
    fun provideDangGiaoInteractor(interactor: DangGiaoInteractor): DangGiaoMVPInteractor = interactor
    @Provides
    fun provideDangGiaoPresenter(presenter: DangGiaoPresenter): DangGiaoMVPPresenter = presenter
}