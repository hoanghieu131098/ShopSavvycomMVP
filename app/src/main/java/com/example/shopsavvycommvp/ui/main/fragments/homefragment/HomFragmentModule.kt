package com.example.shopsavvycommvp.ui.main.fragments.homefragment

import com.example.shopsavvycommvp.ui.main.fragments.homefragment.interactor.HomeFragmentInteractor
import com.example.shopsavvycommvp.ui.main.fragments.homefragment.interactor.HomeFragmentMVPInteractor
import com.example.shopsavvycommvp.ui.main.fragments.homefragment.presenter.HomeFragmentMVPPresenter
import com.example.shopsavvycommvp.ui.main.fragments.homefragment.presenter.HomeFragmentPresenter
import com.example.shopsavvycommvp.ui.main.fragments.homefragment.view.HomeFragmentMVPView
import dagger.Module
import dagger.Provides
@Module
class HomFragmentModule {
    @Provides
    internal fun provideHomeFragmentInteractor(interactor: HomeFragmentInteractor): HomeFragmentMVPInteractor {
        return interactor
    }
    @Provides
    internal fun provideHomeFragmentPresenter(presenter: HomeFragmentPresenter)
        : HomeFragmentMVPPresenter = presenter
}