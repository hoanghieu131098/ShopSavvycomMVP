package com.example.shopsavvycommvp.ui.helpcentre

import com.example.shopsavvycommvp.ui.helpcentre.view.HelpCentreFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HelpCentreFragmentProvider {
    @ContributesAndroidInjector(modules = [HelpCentreFragmentModule::class])
    abstract fun provideHelpCentreFragment(): HelpCentreFragment
}