package com.example.shopsavvycommvp.ui.detailproduct.fragments.ratingfragment.presenter

import android.util.Log
import com.example.shopsavvycommvp.ui.base.presenter.BasePresenter
import com.example.shopsavvycommvp.ui.detailproduct.fragments.ratingfragment.interactor.RatingFragmentMVPInteractor
import com.example.shopsavvycommvp.ui.detailproduct.fragments.ratingfragment.view.RatingFragmentMVPView
import com.example.shopsavvycommvp.util.AppConstants
import com.example.shopsavvycommvp.util.SchedulerProvider
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

    override fun getUpLoadComment(
        username: String,
        images: String,
        content: String,
        reviews: Int,
        idProduct: Int
    ) {
        getView()?.showProgress()
        interactor?.let {
            compositeDisposable.add(it.uploadComment(username, images, content, reviews, idProduct)
                .compose(schedulerProvider.ioToMainObservableScheduler())
                .subscribe({ response ->
                    Log.d("TAGCOMMENT",response)
                    getView()?.let {
                        it.hideProgress()
                        if (response == null) {
                            getView()?.getCommentFailed("Error")
                            return@subscribe
                        }
                        getView()?.uploadCommentSuccess(response)
                        Log.d("TAGCOMMENT","okok")
                    }
                }, {  error -> getView()?.handleThrowableError(error) })
            )
        }
    }

    override fun getUpLoadreply(username: String, image: String, content: String, idComment: Int) {
        getView()?.showProgress()
        interactor?.let {
            compositeDisposable.add(
                it.uploadReply(username, image, content, idComment)
                    .compose(schedulerProvider.ioToMainObservableScheduler())
                    .subscribe({ response ->
                        getView()?.let {
                            if (getView() == null) return@subscribe
                            it.hideProgress()
                            Log.d("TAGRELY",response)
                            if (response!!.contentEquals(AppConstants.Rate.UPLOAD_REPLY_FAILED)) {
                                getView()?.uploadReplyFailed(response)
                                return@subscribe
                            }
                            getView()?.uploadReplySuccess(response)
                        }
                    }, { error -> getView()?.handleThrowableError(error) })
            )
        }
    }

    override fun getIdProduct(idProduct: String) {
        getView()?.showProgress()
        interactor?.let {
            compositeDisposable.add(
                it.getComment(idProduct)
                    .compose(schedulerProvider.ioToMainObservableScheduler())
                    .subscribe({ response ->
                        getView()?.let {
                            if (getView() == null) return@subscribe
                            it.hideProgress()
                            if (response == null) {
                                it.getCommentFailed("Error")
                                return@subscribe
                            }
                            it.getCommentSuccess(response)
                        }
                    }, { error -> getView()?.handleThrowableError(error) })
            )
        }
    }
}