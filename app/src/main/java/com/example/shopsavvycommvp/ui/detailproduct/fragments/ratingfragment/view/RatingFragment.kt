package com.example.shopsavvycommvp.ui.detailproduct.fragments.ratingfragment.view

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.shopsavvycommvp.R
import com.example.shopsavvycommvp.data.network.response.Comment
import com.example.shopsavvycommvp.data.network.response.Product
import com.example.shopsavvycommvp.ui.base.view.BaseFragment
import com.example.shopsavvycommvp.ui.detailproduct.activities.view.DetailActivity
import com.example.shopsavvycommvp.ui.detailproduct.fragments.ratingfragment.presenter.RatingFragmentPresenter
import com.example.shopsavvycommvp.ui.detailproduct.fragments.ratingfragment.view.adapter.Comment_Reply_adapter
import com.example.shopsavvycommvp.util.extension.hideSoftKeyboard
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.hsalf.smilerating.SmileRating
import kotlinx.android.synthetic.main.fragment_rating.*
import javax.inject.Inject


class RatingFragment : BaseFragment(), RatingFragmentMVPView, Comment_Reply_adapter.checkClickReply {


    override val layoutId: Int
        get() = R.layout.fragment_rating

    @Inject
    internal lateinit var presenter: RatingFragmentPresenter
    private lateinit var act: DetailActivity
    private var mProduct: Product? = null
    private lateinit var adapterComment: Comment_Reply_adapter
    private var mdataComment: ArrayList<Comment> = arrayListOf()
    private var mPositioncomment: Int? = null
    private var mUsername: String? = null
    private var mImage: String? = null
    private var mIdComment: Int? = null
    private var mReview: Int = 0
    private lateinit var mAuth: FirebaseAuth
    private var mUser: FirebaseUser? = null
    private val TAG = "selectedSmily"

    override fun setUp() {
        presenter.onAttach(this)
        //init user get from firebase
        mAuth = FirebaseAuth.getInstance()
        mUser = mAuth.currentUser
        mUsername = mUser?.displayName
        mImage = mUser?.photoUrl.toString()
        getInforFromDetail()
        initView()
        setRecycle()
    }

    private fun initView() {
        checkSmilyReview()
        img_send_reply.setOnClickListener {
            if (ed_input_reply.text.toString().trim().isNotEmpty()) {
                presenter.getUpLoadreply(mUsername ?: "", mImage ?: "", ed_input_reply.text.toString().trim(), mdataComment[mPositioncomment ?: 0].id,
                    mdataComment[mPositioncomment ?: 0].reply?.size ?: 0, mProduct?.id ?: 0
                )
            } else {
                Toast.makeText(requireContext(), getString(R.string.not_enter_content), Toast.LENGTH_SHORT).show()
            }
        }
        btn_sendreview.setOnClickListener {
            if (ed_contentreview.text.toString().trim().isNotEmpty()) {
                    mProduct?.id?.let {
                        presenter.getUpLoadComment(
                            mUsername ?: "",
                            mImage ?: "",
                            ed_contentreview.text.toString().trim(),
                            mReview,
                            it,
                            mdataComment.size ?: 0
                        )

                }
            } else {
                this.showError(getString(R.string.you_did_not_enter_content))
            }
        }
    }

    private fun setRecycle() {
        adapterComment = Comment_Reply_adapter(requireContext())
        recycle_Rate.setHasFixedSize(true)
        recycle_Rate.adapter = adapterComment
        adapterComment.setListenerstatusClickReply(this)
    }

    override fun positionComment(position: Int) {
        mPositioncomment = position
        relative_input_reply.visibility = View.VISIBLE
        tv_username_reply.text = mPositioncomment?.let { it1 -> mdataComment.get(it1).username }
    }

    override fun checkstatus(status: Boolean) {
    }

    override fun getCommentFailed(msg: String) {
        this.showError(msg)
    }

    override fun getCommentSuccess(msg: ArrayList<Comment>) {
            mdataComment.clear()
            mdataComment.addAll(msg)
            adapterComment.setData(msg)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    private fun getInforFromDetail() {
        act = activity as DetailActivity
        act.getDescription().observe(act, object : Observer<Product?> {
            override fun onChanged(t: Product?) {
                mProduct = t
                t?.id?.let { presenter.getCommentFollowProduct(it) }
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

    override fun uploadReplyFailed() {
        Toast.makeText(requireContext(), getString(R.string.reply_upload_error) , Toast.LENGTH_SHORT).show()
    }

    override fun uploadReplySuccess() {
        Toast.makeText(requireContext(), getString(R.string.reply_upload_success), Toast.LENGTH_SHORT).show()
        relative_input_reply.visibility = View.INVISIBLE
        ed_input_reply.setText("")
    }

    override fun uploadCommentFailed() {
        Toast.makeText(requireContext(), getString(R.string.comment_upload_error), Toast.LENGTH_SHORT).show()
    }

    override fun uploadCommentSuccess() {
        Toast.makeText(requireContext(), getString(R.string.comment_upload_success), Toast.LENGTH_SHORT).show()
        ed_contentreview.setText("")
        requireActivity().hideSoftKeyboard()
        recycle_Rate.scrollToPosition(mdataComment.size - 1)
    }

}