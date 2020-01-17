package com.example.shopsavvycommvp.ui.main.fragments.fashionfragment

import com.example.shopsavvycommvp.ui.main.fragments.fashionfragment.interactor.FashionFragmentInteractor
import com.example.shopsavvycommvp.ui.main.fragments.fashionfragment.interactor.FashionFragmentMVPInteractor
import com.example.shopsavvycommvp.ui.main.fragments.fashionfragment.presenter.FashionFragmentMVPPresenter
import com.example.shopsavvycommvp.ui.main.fragments.fashionfragment.presenter.FashionFragmentPresenter

import dagger.Module
import dagger.Provides

@Module
class FashionFragmentModule {
    @Provides
    fun provideFashionFragmentInteractor(interactor: FashionFragmentInteractor): FashionFragmentMVPInteractor {
        return interactor
    }
    fun provideFashionFragmentPresenter(presenter: FashionFragmentPresenter) : FashionFragmentMVPPresenter {
        return presenter
    }
}