package com.example.shopsavvycommvp.ui.notiorder.fragments.cholayhang

import com.example.shopsavvycommvp.ui.notiorder.fragments.cholayhang.view.ChoLayHangFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ChoLayHangFragmentProvider {
    @ContributesAndroidInjector(modules = [ChoLayHangFragmentModule::class])
    abstract fun provideChoLayHangFragent(): ChoLayHangFragment
}