package com.example.shopsavvycommvp.ui.notiorder.fragments.choxacnhan

import com.example.shopsavvycommvp.ui.notiorder.fragments.choxacnhan.view.ChoXacNhanFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ChoXacNhanFragmentProvider {
    @ContributesAndroidInjector(modules = [ChoXacNhanFragmentModule::class])
    abstract fun provideChoXacNhanFragment(): ChoXacNhanFragment
}