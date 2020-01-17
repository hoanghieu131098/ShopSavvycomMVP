package com.example.shopsavvycommvp.ui.detailproduct.fragments.inforfragment

import com.example.shopsavvycommvp.ui.detailproduct.fragments.inforfragment.view.InforFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class InforFragmentProvider {
    @ContributesAndroidInjector(modules = [InforFragmentModule::class])
    abstract fun provideInforFragment(): InforFragment
}