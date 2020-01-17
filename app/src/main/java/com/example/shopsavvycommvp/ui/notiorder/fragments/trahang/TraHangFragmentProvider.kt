package com.example.shopsavvycommvp.ui.notiorder.fragments.trahang

import com.example.shopsavvycommvp.ui.notiorder.fragments.trahang.view.TraHangFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TraHangFragmentProvider {
    @ContributesAndroidInjector(modules = [TraHangFragmentModule::class])
    abstract fun provideTraHangFragment(): TraHangFragment
}