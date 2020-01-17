package com.example.shopsavvycommvp.ui.detailproduct.fragments.ratingfragment

import com.example.shopsavvycommvp.ui.detailproduct.fragments.ratingfragment.interactor.RatingFragmentInteractor
import com.example.shopsavvycommvp.ui.detailproduct.fragments.ratingfragment.interactor.RatingFragmentMVPInteractor
import com.example.shopsavvycommvp.ui.detailproduct.fragments.ratingfragment.presenter.RatingFragmentMVPPresenter
import com.example.shopsavvycommvp.ui.detailproduct.fragments.ratingfragment.presenter.RatingFragmentPresenter
import dagger.Module
import dagger.Provides

@Module
class RatingFragmentModule {
    @Provides
    internal fun provideRatingFragmentInteractor(interactor: RatingFragmentInteractor): RatingFragmentMVPInteractor = interactor
    @Provides
    internal  fun provideRatingFragmentPresenter(presenter: RatingFragmentPresenter): RatingFragmentMVPPresenter = presenter
}