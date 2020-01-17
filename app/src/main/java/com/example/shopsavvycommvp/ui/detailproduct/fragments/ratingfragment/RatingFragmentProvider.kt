package com.example.shopsavvycommvp.ui.detailproduct.fragments.ratingfragment

import com.example.shopsavvycommvp.ui.detailproduct.fragments.ratingfragment.view.RatingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class RatingFragmentProvider {
 @ContributesAndroidInjector(modules = [RatingFragmentModule::class])
 abstract fun provideRatingFragment():RatingFragment
}