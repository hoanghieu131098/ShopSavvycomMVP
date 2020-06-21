package com.example.shopsavvycommvp.ui.base.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopsavvycommvp.R
import com.example.shopsavvycommvp.data.network.request.Cart
import com.example.shopsavvycommvp.data.network.request.Order
import com.example.shopsavvycommvp.data.network.response.BaseModel
import com.example.shopsavvycommvp.data.network.response.Category
import com.example.shopsavvycommvp.data.network.response.Product
import com.example.shopsavvycommvp.model.Slider
import com.example.shopsavvycommvp.util.AppConstants
import com.example.shopsavvycommvp.util.extension.loadImg

import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_category_home_fm.view.*
import kotlinx.android.synthetic.main.item_clothes_home.view.*
import kotlinx.android.synthetic.main.item_detail_order.view.*
import kotlinx.android.synthetic.main.item_order.view.*
import kotlinx.android.synthetic.main.item_product_cart.view.*
import java.lang.Exception
import java.text.SimpleDateFormat

class BaseAdapter<T : BaseModel>(val context: Context, val layout: Int) :
    RecyclerView.Adapter<BaseAdapter.BaseViewholder>() {
    private var mdata: ArrayList<T>? = arrayListOf()
    private var mListiner: onBaseClickListener? = null

    fun setBaseClickListener(Listiner: onBaseClickListener) {
        this.mListiner = Listiner
    }

    fun setData(data: ArrayList<T>) {
        this.mdata?.clear()
        this.mdata?.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewholder {
        return BaseViewholder(LayoutInflater.from(context).inflate(layout, parent, false))
    }

    override fun getItemCount(): Int {
        return if (mdata != null)
            mdata!!.size
        else 0
    }

    override fun onBindViewHolder(holder: BaseViewholder, position: Int) {
        mdata?.get(position)?.let { holder.bind(it, position) }
        if (mListiner != null) {
            holder.itemView.setOnClickListener {
                mdata?.get(position)?.let { it1 -> mListiner?.onItemBaseClick(it1) }
            }
        }
    }

    class BaseViewholder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        @SuppressLint("SimpleDateFormat")
        fun bind(T: BaseModel, position: Int) {
            when (T) {
                is Product -> {
                    val wScreen = Resources.getSystem().displayMetrics.widthPixels
                    val w = wScreen / 2 - 40
                    val params = itemView.layoutParams as RecyclerView.LayoutParams
                    params.apply {
                        width = w
                        if (position % 2 == 0) {
                            leftMargin =
                                itemView.resources.getDimensionPixelOffset(R.dimen.margin_5dp)
                            rightMargin =
                                itemView.resources.getDimensionPixelOffset(R.dimen.with_h_2dp)
                        } else {
                            leftMargin =
                                itemView.resources.getDimensionPixelOffset(R.dimen.with_h_2dp)
                            rightMargin =
                                itemView.resources.getDimensionPixelOffset(R.dimen.margin_5dp)
                        }
                    }

                    itemView.ln_item_home.layoutParams = params
                    itemView.img_clothes_home.loadImg(T.dataimage?.get(0))
                    itemView.tv_clothes_name_item_home.text = T.name
                    itemView.tv_clothes_price_item_home.text = "$ ${T.price}"

                }
                is Category -> {
                    itemView.img_item_category.loadImg(T.image)
                    itemView.tv_name_item_category.text = T.name
                }
                is Order -> {
                    itemView.imgItemOrder.loadImg(T.listCart!![0].product!!.dataimage!!.get(0))
                    itemView.tvItemOrderName.text = "Đơn hàng ${T.id} đã Order thành công!"
                    val date = SimpleDateFormat("yyyy/MM/dd 'at' HH:mm:ss")
                    itemView.tvItemDateTimeOrder.text = date.format(T.dateTime)
                    when (T.active) {
                        false -> itemView.lnItemOrder.setBackgroundColor(Color.rgb(224, 247, 250))
                        true -> itemView.lnItemOrder.setBackgroundColor(Color.WHITE)
                    }
                }
            }

        }
    }

    interface onBaseClickListener {
        fun onItemBaseClick(T: BaseModel)
    }

}