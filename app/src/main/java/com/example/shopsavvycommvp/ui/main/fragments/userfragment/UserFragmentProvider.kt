package com.example.shopsavvycommvp.ui.main.fragments.userfragment

import com.example.shopsavvycommvp.ui.helpcentre.HelpCentreFragmentModule
import com.example.shopsavvycommvp.ui.helpcentre.view.HelpCentreFragment
import com.example.shopsavvycommvp.ui.main.fragments.userfragment.view.UserFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UserFragmentProvider {

    @ContributesAndroidInjector(modules = [UserFragmentModule::class])
    abstract fun provideUserFragment(): UserFragment

    @ContributesAndroidInjector(modules = [HelpCentreFragmentModule::class])
    abstract fun provideHelpCentreFragment(): HelpCentreFragment
}