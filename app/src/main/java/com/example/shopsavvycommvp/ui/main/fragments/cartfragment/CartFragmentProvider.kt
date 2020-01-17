package com.example.shopsavvycommvp.ui.main.fragments.cartfragment

import com.example.shopsavvycommvp.ui.main.fragments.cartfragment.view.CartFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CartFragmentProvider {
    @ContributesAndroidInjector(modules = [CartFragmentModule::class])
    abstract fun provideCartFragment(): CartFragment
}