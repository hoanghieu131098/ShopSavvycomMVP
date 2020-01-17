package com.example.shopsavvycommvp.ui.main.fragments.cartfragment

import com.example.shopsavvycommvp.ui.main.fragments.cartfragment.interactor.CartFragmentInteractor
import com.example.shopsavvycommvp.ui.main.fragments.cartfragment.interactor.CartFragmentMVPInteractor
import com.example.shopsavvycommvp.ui.main.fragments.cartfragment.presenter.CartFragmentMVPPresenter
import com.example.shopsavvycommvp.ui.main.fragments.cartfragment.presenter.CartFragmentPresenter
import dagger.Module
import dagger.Provides

@Module
class CartFragmentModule {
    @Provides
    fun provideCartFragmentInteractor(interactor: CartFragmentInteractor): CartFragmentMVPInteractor {
        return interactor
    }
    @Provides
    fun provideCartFragmentPresenter(presenter: CartFragmentPresenter): CartFragmentMVPPresenter{
        return presenter
    }

}