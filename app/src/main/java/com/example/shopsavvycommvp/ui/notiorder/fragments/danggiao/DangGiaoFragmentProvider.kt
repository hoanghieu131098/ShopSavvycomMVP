package com.example.shopsavvycommvp.ui.notiorder.fragments.danggiao

import com.example.shopsavvycommvp.ui.notiorder.fragments.danggiao.view.DangGiaoFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DangGiaoFragmentProvider {
    @ContributesAndroidInjector(modules = [DangGiaoFragmentModule::class])
    abstract fun provideDangGiaoFragment(): DangGiaoFragment
}