package com.example.shopsavvycommvp.ui.detailproduct.fragments.ratingfragment.view

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.shopsavvycommvp.R

import com.example.shopsavvycommvp.data.network.response.Comment
import com.example.shopsavvycommvp.data.network.response.Product
import com.example.shopsavvycommvp.data.network.response.Reply
import com.example.shopsavvycommvp.ui.base.view.BaseFragment
import com.example.shopsavvycommvp.ui.detailproduct.activities.view.DetailActivity
import com.example.shopsavvycommvp.ui.detailproduct.fragments.ratingfragment.presenter.RatingFragmentPresenter
import com.example.shopsavvycommvp.ui.detailproduct.fragments.ratingfragment.view.adapter.Comment_Reply_adapter
import com.example.shopsavvycommvp.ui.detailproduct.fragments.ratingfragment.view.adapter.checkClickReply
import com.example.shopsavvycommvp.util.AppConstants

import com.github.ybq.android.spinkit.style.Circle
import com.github.ybq.android.spinkit.style.ThreeBounce
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_infor.*
import kotlinx.android.synthetic.main.fragment_rating.*
import kotlinx.android.synthetic.main.item_rate_clothes.*
import javax.inject.Inject
import com.hsalf.smilerating.SmileRating
import com.hsalf.smilerating.BaseRating


class RatingFragment : BaseFragment(), RatingFragmentMVPView, checkClickReply {


    override val layoutId: Int
        get() = R.layout.fragment_rating

    @Inject
    internal lateinit var presenter: RatingFragmentPresenter
    private lateinit var act: DetailActivity
    private var mIdProduct: String? = null
    private var adapterComment: Comment_Reply_adapter? = null
    private var mdataComment: ArrayList<Comment>? = null
    private var mPositioncomment: Int? = null
    private val TAG = "selectedSmily"

    private lateinit var mAuth: FirebaseAuth
    private var mUser: FirebaseUser? = null


    override fun setUp() {
        presenter.onAttach(this)
        //init user get from firebase
        mAuth = FirebaseAuth.getInstance()
        mUser = mAuth.currentUser
        mUsername = mUser!!.displayName
        mImage = mUser!!.photoUrl.toString()

        getInforFromDetail()
        initView()
        setRecycle()
    }

    private var mUsername: String? = null
    private var mContentReply: String? = null
    private var mContentComment: String? = null
    private var mImage: String? = null
    private var mIdComment: Int? = null
    private var mReview: Int = 0
    private fun initView() {
        checkSmilyReview()
        img_send_reply.setOnClickListener {
            Log.d("testPositionComment", "" + mPositioncomment)
            mContentReply = ed_input_reply.text.toString()
            mIdComment = mPositioncomment?.let { it1 -> mdataComment!!.get(it1).id }
            Log.d("Comment", "" + mIdComment)
            if (mContentReply!!.isEmpty()) {
                Toast.makeText(requireContext(), "You did not enter content!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                presenter.getUpLoadreply(mUsername!!, mImage!!, mContentReply!!, mIdComment!!)
            }
        }
        btn_sendreview.setOnClickListener {

            mContentComment = ed_contentreview.text.toString()

            Log.d("testPositionComment", "" + mReview)
            if (mContentComment.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "You did not enter content!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                presenter.getUpLoadComment(
                    mUsername!!,
                    mImage!!,
                    mContentComment!!,
                    mReview,
                    mIdProduct!!.toInt()
                )
            }
        }
    }

    private fun setRecycle() {
        adapterComment = Comment_Reply_adapter(requireContext())
        recycle_Rate.setHasFixedSize(true)
        recycle_Rate.adapter = adapterComment
        adapterComment?.setListenerstatusClickReply(this)
    }

    override fun PositionComment(position: Int) {
        mPositioncomment = position
        Log.d("testPositionComment", "" + mPositioncomment)
        relative_input_reply.visibility = View.VISIBLE
        tv_username_reply.text = mPositioncomment?.let { it1 -> mdataComment?.get(it1)?.username }
    }

    override fun checkstatus(status: Boolean) {

    }

    override fun getCommentFailed(msg: String) {

    }

    override fun getCommentSuccess(msg: List<Comment>) {
        mdataComment = ArrayList()
        mdataComment?.addAll(msg as ArrayList<Comment>)
        adapterComment?.setData(msg as ArrayList<Comment>)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    private fun getInforFromDetail() {
        act = activity as DetailActivity
        act.getIdProduct().observe(act, object : Observer<String?> {
            override fun onChanged(t: String?) {
                mIdProduct = t
                presenter.getIdProduct(mIdProduct.toString())
                Log.d("testProduct", "" + mIdProduct.toString())
            }
        })
    }

    //check smily comment
    private fun checkSmilyReview(){
        smile_rating.setOnSmileySelectionListener(SmileRating.OnSmileySelectionListener { smiley, _ ->
            when (smiley) {
                SmileRating.BAD -> {
                    Log.d(TAG, "Bad")
                    mReview = 2
                }
                SmileRating.GOOD -> {
                    Log.d(TAG, "Good")
                    mReview = 4

                }
                SmileRating.GREAT -> {
                    Log.d(TAG, "Great")
                    mReview = 5
                }
                SmileRating.OKAY -> {
                    Log.d(TAG, "Okay")
                    mReview = 3
                }
                SmileRating.TERRIBLE -> {
                    Log.d(TAG, "Terrible")
                    mReview = 1
                }
            }
        })

    }

    override fun uploadReplyFailed(msg: String) {
        Toast.makeText(requireContext(), "Reply " + msg, Toast.LENGTH_SHORT).show()
    }

    override fun uploadReplySuccess(msg: String) {
        Toast.makeText(requireContext(), "Reply " + msg, Toast.LENGTH_SHORT).show()

        mdataComment!!.get(mPositioncomment!!)
            .reply?.add(
            Reply(
                mdataComment!!.get(mPositioncomment!!).reply!!.size,
                mUsername!!,
                mImage!!,
                mContentReply!!,
                mIdComment!!
            )
        )

        adapterComment!!.setData(mdataComment!!)
        relative_input_reply.visibility = View.INVISIBLE
        ed_input_reply.text = null
    }

    override fun uploadCommentFailed(msg: String) {
        Toast.makeText(requireContext(), "Comment " + msg, Toast.LENGTH_SHORT).show()
    }

    override fun uploadCommentSuccess(msg: String) {
        Toast.makeText(requireContext(), "Comment " + msg, Toast.LENGTH_SHORT).show()

        val arrReply: ArrayList<Reply> = arrayListOf()
        mdataComment!!.add(
            Comment(
                mdataComment!!.size,
                mUsername!!,
                mImage!!,
                mContentComment!!,
                mReview,
                mIdProduct!!.toInt(),
                arrReply
            )
        )
        adapterComment!!.setData(mdataComment!!)
        ed_contentreview.text = null

    }

}