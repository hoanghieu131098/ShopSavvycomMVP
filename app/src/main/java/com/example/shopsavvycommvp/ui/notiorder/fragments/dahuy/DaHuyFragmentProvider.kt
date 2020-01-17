package com.example.shopsavvycommvp.ui.notiorder.fragments.dahuy

import com.example.shopsavvycommvp.ui.notiorder.fragments.dahuy.view.DaHuyFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DaHuyFragmentProvider {
    @ContributesAndroidInjector(modules = [DaHuyFragmentModule::class])
    abstract fun provideDaHuyFragment(): DaHuyFragment
}