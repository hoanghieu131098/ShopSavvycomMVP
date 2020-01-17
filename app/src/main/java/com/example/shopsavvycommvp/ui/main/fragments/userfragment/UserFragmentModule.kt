package com.example.shopsavvycommvp.ui.main.fragments.userfragment

import com.example.shopsavvycommvp.ui.main.fragments.userfragment.interactor.UserFragmentInteractor
import com.example.shopsavvycommvp.ui.main.fragments.userfragment.interactor.UserFragmentMVPInteractor
import com.example.shopsavvycommvp.ui.main.fragments.userfragment.presenter.UserFragmentMVPPresenter
import com.example.shopsavvycommvp.ui.main.fragments.userfragment.presenter.UserFragmentPresenter
import dagger.Module
import dagger.Provides

@Module
class UserFragmentModule {
    @Provides
    fun provideUserFragmentInteractor(interactor: UserFragmentInteractor): UserFragmentMVPInteractor{
        return interactor
    }
    @Provides
    fun provideUserFragmentPresenter(presenter: UserFragmentPresenter) : UserFragmentMVPPresenter{
        return presenter
    }
}