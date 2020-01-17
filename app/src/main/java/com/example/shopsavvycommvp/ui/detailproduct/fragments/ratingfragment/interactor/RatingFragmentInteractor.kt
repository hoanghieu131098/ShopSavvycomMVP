package com.example.shopsavvycommvp.ui.detailproduct.fragments.ratingfragment.interactor

import com.example.shopsavvycommvp.data.network.AppApi
import com.example.shopsavvycommvp.data.network.response.Comment
import com.example.shopsavvycommvp.ui.base.interactor.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject

class RatingFragmentInteractor @Inject constructor(apiHelper: AppApi) : BaseInteractor(apiHelper),
    RatingFragmentMVPInteractor {
    override fun uploadComment(
        username: String,
        images: String,
        content: String,
        reviews: Int,
        idProduct: Int
    ): Observable<String> {
        return appApi.uploadComment(username,images,content,reviews,idProduct)
    }

    override fun uploadReply(
        username: String,
        image: String,
        content: String,
        idComment: Int
    ): Observable<String> {
        return appApi.uploadReply(username, image, content, idComment)
    }

    override fun getComment(idProduct: String): Observable<List<Comment>?> {
        return appApi.getComment(idProduct)
    }
}