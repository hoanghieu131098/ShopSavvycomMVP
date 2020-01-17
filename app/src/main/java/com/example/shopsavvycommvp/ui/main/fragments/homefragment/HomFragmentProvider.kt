package com.example.shopsavvycommvp.ui.main.fragments.homefragment

import com.example.shopsavvycommvp.ui.main.fragments.homefragment.view.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
 abstract class HomFragmentProvider {

    @ContributesAndroidInjector(modules = [HomFragmentModule::class])
    abstract fun provideHomeFragment(): HomeFragment
}