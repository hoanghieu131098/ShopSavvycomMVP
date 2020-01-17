package com.example.shopsavvycommvp.ui.detailproduct.fragments.ratingfragment.view.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopsavvycommvp.R
import com.example.shopsavvycommvp.data.network.response.Comment
import com.example.shopsavvycommvp.data.network.response.Reply
import com.example.shopsavvycommvp.ui.detailproduct.activities.view.adapter.SliderAdapter
import com.example.shopsavvycommvp.util.extension.loadImg

import kotlinx.android.synthetic.main.item_rate_clothes.view.*
import kotlinx.android.synthetic.main.item_reply.view.*

class Comment_Reply_adapter(val context: Context) :
    RecyclerView.Adapter<Comment_Reply_adapter.viewholder>() {
    private var dataReview: ArrayList<Comment>? = null
    private var listenerStatusCickReply: checkClickReply? = null
//    private var selectedPosition = -1
    fun setListenerstatusClickReply(listenerStatusCickReply: checkClickReply) {
        this.listenerStatusCickReply = listenerStatusCickReply
    }

    fun setData(dataReview: ArrayList<Comment>) {
        Log.d("TAGCOMMENT","okokok" + dataReview!!.size)
        this.dataReview = dataReview
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(
            LayoutInflater.from(this.context).inflate(
                R.layout.item_rate_clothes,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return if (dataReview != null)
            return dataReview!!.size
        else 0

    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.bind(dataReview!!.get(position))
        holder.itemView.tv_reply_comment.setOnClickListener {
            this.listenerStatusCickReply?.checkstatus(true)
            this.listenerStatusCickReply?.PositionComment(position)
//            selectedPosition = position
        }
//        if(selectedPosition == position){
//            holder.itemView.tv_reply_comment.setTextColor(Color.BLUE)
//        }else{
//
//        }
    }

    class viewholder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        fun bind(review: Comment) {
            itemView.img_item_reviewer.loadImg(review.image)
            itemView.tv_item_name_reviewer.text = review.username
            itemView.tv_item_content_reviewer.text = review.content
            when (review.review) {
                1 -> itemView.img_item_comment.setImageResource(R.drawable.terrible)
                2 -> itemView.img_item_comment.setImageResource(R.drawable.bad)
                3 -> itemView.img_item_comment.setImageResource(R.drawable.okay)
                4 -> itemView.img_item_comment.setImageResource(R.drawable.good)
                5 -> itemView.img_item_comment.setImageResource(R.drawable.great)
                0 -> itemView.img_item_comment.visibility = View.INVISIBLE
            }

            val adapter = review.reply?.let { ReplyAdapter(itemView.context, it) }
            itemView.recycle_reply_item_comment.setHasFixedSize(true)
            itemView.recycle_reply_item_comment.adapter = adapter

        }
    }
}

class ReplyAdapter(val context: Context, val datareply: ArrayList<Reply>) :
    RecyclerView.Adapter<ReplyAdapter.viewholerReply>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholerReply {
        return viewholerReply(
            LayoutInflater.from(context).inflate(
                R.layout.item_reply,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return if (datareply != null)
            datareply!!.size
        else 0
    }

    override fun onBindViewHolder(holder: viewholerReply, position: Int) {
        holder.bind(datareply!!.get(position))
    }

    class viewholerReply(itemview: View) : RecyclerView.ViewHolder(itemview) {
        fun bind(reply: Reply) {
            itemView.img_item_replyer.loadImg(reply.image)
            itemView.tv_item_name_replyer.text = reply.username
            itemView.tv_item_content_replyer.text = reply.content
        }
    }
}

interface checkClickReply {
    fun checkstatus(status: Boolean)
    fun PositionComment(position: Int)
}