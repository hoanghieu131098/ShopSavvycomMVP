package com.example.shopsavvycommvp.ui.detailproduct.fragments.inforfragment

import com.example.shopsavvycommvp.ui.detailproduct.fragments.inforfragment.interactor.InforFragmentInteractor
import com.example.shopsavvycommvp.ui.detailproduct.fragments.inforfragment.interactor.InforFragmentMVPInteractor
import com.example.shopsavvycommvp.ui.detailproduct.fragments.inforfragment.presenter.InforFragmentMVPPresenter
import com.example.shopsavvycommvp.ui.detailproduct.fragments.inforfragment.presenter.InforFragmentPresenter
import dagger.Module
import dagger.Provides

@Module
class InforFragmentModule {
    @Provides
    internal fun provideInforFragmentInteractor(interactor: InforFragmentInteractor): InforFragmentMVPInteractor = interactor
    @Provides
    internal fun provideInforFragmentPresenter(presenter: InforFragmentPresenter): InforFragmentMVPPresenter = presenter
}