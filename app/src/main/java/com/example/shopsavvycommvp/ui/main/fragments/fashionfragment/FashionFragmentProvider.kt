package com.example.shopsavvycommvp.ui.main.fragments.fashionfragment

import com.example.shopsavvycommvp.ui.main.fragments.fashionfragment.view.FashionFragment
import com.example.shopsavvycommvp.ui.main.fragments.userfragment.view.UserFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FashionFragmentProvider {

    @ContributesAndroidInjector(modules = [FashionFragmentModule::class])
    abstract fun provideFashionFragment(): FashionFragment
}