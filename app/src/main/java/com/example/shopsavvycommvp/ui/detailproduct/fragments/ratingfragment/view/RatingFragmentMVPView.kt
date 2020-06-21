package com.example.shopsavvycommvp.ui.detailproduct.fragments.ratingfragment.view

import com.example.shopsavvycommvp.data.network.response.Comment
import com.example.shopsavvycommvp.ui.base.view.MVPView

interface RatingFragmentMVPView : MVPView{
    fun getCommentFailed(msg: String)
    fun getCommentSuccess(msg: ArrayList<Comment>)

    fun uploadReplyFailed()
    fun uploadReplySuccess()

    fun uploadCommentFailed()
    fun uploadCommentSuccess()


}