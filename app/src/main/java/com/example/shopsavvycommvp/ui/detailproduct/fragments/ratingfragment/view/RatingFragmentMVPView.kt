package com.example.shopsavvycommvp.ui.detailproduct.fragments.ratingfragment.view

import com.example.shopsavvycommvp.data.network.response.Comment
import com.example.shopsavvycommvp.ui.base.view.MVPView

interface RatingFragmentMVPView : MVPView{
    fun getCommentFailed(msg: String)
    fun getCommentSuccess(msg: List<Comment>)

    fun uploadReplyFailed(msg: String)
    fun uploadReplySuccess(msg: String)

    fun uploadCommentFailed(msg: String)
    fun uploadCommentSuccess(msg: String)


}