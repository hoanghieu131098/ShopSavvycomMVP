package com.example.shopsavvycommvp.ui.detailproduct.fragments.ratingfragment.interactor

import com.example.shopsavvycommvp.data.network.response.Comment
import com.example.shopsavvycommvp.ui.base.interactor.MVPInteractor
import io.reactivex.Observable

interface RatingFragmentMVPInteractor : MVPInteractor {
    fun getComment(idProduct: String): Observable<List<Comment>?>
    fun uploadReply(
        username: String,
        image: String,
        content: String,
        idComment: Int
    ): Observable<String>

    fun uploadComment(
        username: String,
        images: String,
        content: String,
        reviews: Int,
        idProduct: Int
    ): Observable<String>
}