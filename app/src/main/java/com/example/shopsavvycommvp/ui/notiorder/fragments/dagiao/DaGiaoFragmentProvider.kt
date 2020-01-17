package com.example.shopsavvycommvp.ui.notiorder.fragments.dagiao

import com.example.shopsavvycommvp.ui.notiorder.fragments.dagiao.view.DaGiaoFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DaGiaoFragmentProvider {
    @ContributesAndroidInjector(modules = [DaGiaoFragmentModule::class])
    abstract fun provideDaGiaoFragment(): DaGiaoFragment
}