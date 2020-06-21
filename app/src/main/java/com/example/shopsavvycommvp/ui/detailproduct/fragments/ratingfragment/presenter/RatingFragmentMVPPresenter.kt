package com.example.shopsavvycommvp.ui.detailproduct.fragments.ratingfragment.presenter

import com.example.shopsavvycommvp.ui.base.presenter.MVPPresenter
import com.example.shopsavvycommvp.ui.detailproduct.fragments.ratingfragment.interactor.RatingFragmentMVPInteractor
import com.example.shopsavvycommvp.ui.detailproduct.fragments.ratingfragment.view.RatingFragmentMVPView

interface RatingFragmentMVPPresenter :
    MVPPresenter<RatingFragmentMVPView, RatingFragmentMVPInteractor> {
    fun getCommentFollowProduct(idProduct: Int)
    fun getUpLoadreply(
        username: String,
        image: String,
        content: String,
        idComment: Int,
        newIdReply: Int,
        idProduct: Int
    )

    fun getUpLoadComment(
        username: String,
        images: String,
        content: String,
        reviews: Int,
        idProduct: Int,
        newIdComment: Int
    )
}