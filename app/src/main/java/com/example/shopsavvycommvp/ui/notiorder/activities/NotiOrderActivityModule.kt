package com.example.shopsavvycommvp.ui.notiorder.activities

import com.example.shopsavvycommvp.ui.notiorder.activities.interactor.NotiOrderInteractor
import com.example.shopsavvycommvp.ui.notiorder.activities.interactor.NotiOrderMVPInteractor
import com.example.shopsavvycommvp.ui.notiorder.activities.presenter.NotiOrderMVPPresenter
import com.example.shopsavvycommvp.ui.notiorder.activities.presenter.NotiOrderPresenter
import com.example.shopsavvycommvp.ui.notiorder.activities.view.NotiOrderMVPView
import dagger.Module
import dagger.Provides

@Module
class NotiOrderActivityModule {
    @Provides
    fun provideNotiOrderInteractor(interactor: NotiOrderInteractor): NotiOrderMVPInteractor = interactor
    @Provides
    fun provideNotiOrderPresenter(presenter: NotiOrderPresenter<NotiOrderMVPView,NotiOrderMVPInteractor>)
    : NotiOrderMVPPresenter<NotiOrderMVPView,NotiOrderMVPInteractor> = presenter
}