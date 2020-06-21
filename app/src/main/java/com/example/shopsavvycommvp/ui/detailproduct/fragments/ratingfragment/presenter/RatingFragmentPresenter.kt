package com.example.shopsavvycommvp.ui.detailproduct.fragments.ratingfragment.presenter

import android.util.Log
import com.example.shopsavvycommvp.R
import com.example.shopsavvycommvp.data.network.response.Comment
import com.example.shopsavvycommvp.data.network.response.Product
import com.example.shopsavvycommvp.data.network.response.Reply
import com.example.shopsavvycommvp.ui.base.presenter.BasePresenter
import com.example.shopsavvycommvp.ui.detailproduct.fragments.ratingfragment.interactor.RatingFragmentMVPInteractor
import com.example.shopsavvycommvp.ui.detailproduct.fragments.ratingfragment.view.RatingFragmentMVPView
import com.example.shopsavvycommvp.util.AppConstants
import com.example.shopsavvycommvp.util.SchedulerProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import io.reactivex.disposables.CompositeDisposable
import java.lang.Error
import javax.inject.Inject


class RatingFragmentPresenter @Inject internal constructor(
    interactor: RatingFragmentMVPInteractor,
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<RatingFragmentMVPView, RatingFragmentMVPInteractor>(
    interactor,
    schedulerProvider,
    disposable
),
    RatingFragmentMVPPresenter {

    override fun getUpLoadComment(username: String, images: String, content: String, reviews: Int, idProduct: Int, newIdComment: Int
    ) {
        getView()?.showProgress()
        interactor?.getFirebase()?.reference?.child(AppConstants.Product.PRODUCT)
            ?.child(idProduct.toString())
            ?.child(AppConstants.Product.COMMENTS)
            ?.child(newIdComment.toString())
            ?.setValue(Comment(newIdComment,username,images,content,reviews,null))
            ?.addOnCompleteListener {
                getView()?.hideProgress()
                getView()?.uploadCommentSuccess()

            }?.addOnFailureListener {
                getView()?.hideProgress()
                getView()?.uploadCommentFailed()
            }
    }

    override fun getUpLoadreply(username: String, image: String, content: String, idComment: Int, newIdReply: Int, idProduct: Int) {
        getView()?.showProgress()
        interactor?.getFirebase()?.reference?.child(AppConstants.Product.PRODUCT)
            ?.child(idProduct.toString())
            ?.child(AppConstants.Product.COMMENTS)
            ?.child(idComment.toString())
            ?.child(AppConstants.Product.REPLY)
            ?.child(newIdReply.toString())?.setValue(Reply(newIdReply,username,image,content))
            ?.addOnCompleteListener {
                getView()?.hideProgress()
                getView()?.uploadReplySuccess()

            }?.addOnFailureListener {
                getView()?.hideProgress()
                getView()?.uploadReplyFailed()
            }
    }

    override fun getCommentFollowProduct(idProduct: Int) {
        getView()?.showProgress()
        interactor?.getFirebase()?.reference?.child(AppConstants.Product.PRODUCT)?.child(idProduct.toString())
            ?.child(AppConstants.Product.COMMENTS)
            ?.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    getView()?.hideProgress()
                    getView()?.getCommentFailed(p0.message)
                }

                override fun onDataChange(p0: DataSnapshot) {
                    getView()?.hideProgress()
                    val listComment = arrayListOf<Comment>()
                    for (i in p0.children) {
                        val comment = i.getValue(Comment::class.java)
                        comment?.let { listComment.add(it) }
                    }
                    getView()?.getCommentSuccess(listComment)
                }
            })
    }
}