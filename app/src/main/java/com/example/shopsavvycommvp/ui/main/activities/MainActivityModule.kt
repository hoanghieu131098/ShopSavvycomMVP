package com.example.shopsavvycommvp.ui.main.activities

import com.example.shopsavvycommvp.ui.main.activities.interactor.MainInteractor
import com.example.shopsavvycommvp.ui.main.activities.interactor.MainMVPInteractor
import com.example.shopsavvycommvp.ui.main.activities.presenter.MainMVPPresenter
import com.example.shopsavvycommvp.ui.main.activities.presenter.MainPresenter
import com.example.shopsavvycommvp.ui.main.activities.view.MainMVPView
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {
    @Provides
    internal fun provideMainInteractor(mainInteractor: MainInteractor): MainMVPInteractor = mainInteractor

    @Provides
    internal fun provideMainPresenter(mainPresenter: MainPresenter<MainMVPView, MainMVPInteractor>)
            : MainMVPPresenter<MainMVPView, MainMVPInteractor> = mainPresenter

}