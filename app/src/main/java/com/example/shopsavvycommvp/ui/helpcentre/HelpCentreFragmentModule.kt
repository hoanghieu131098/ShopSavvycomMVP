package com.example.shopsavvycommvp.ui.helpcentre

import com.example.shopsavvycommvp.ui.helpcentre.interactor.HelpCentreFragmentInteractor
import com.example.shopsavvycommvp.ui.helpcentre.interactor.HelpCentreFragmentMVPInteractor
import com.example.shopsavvycommvp.ui.helpcentre.presenter.HelpCentreFragmentMVPPresenter
import com.example.shopsavvycommvp.ui.helpcentre.presenter.HelpCentreFragmentPresenter
import dagger.Module
import dagger.Provides

@Module
class HelpCentreFragmentModule {
    @Provides
    fun provideHelpCentreFragmentInteractor(interactor: HelpCentreFragmentInteractor): HelpCentreFragmentMVPInteractor = interactor
    @Provides
    fun provideHelpCentreFragmentPresenter(presenter: HelpCentreFragmentPresenter): HelpCentreFragmentMVPPresenter = presenter
}