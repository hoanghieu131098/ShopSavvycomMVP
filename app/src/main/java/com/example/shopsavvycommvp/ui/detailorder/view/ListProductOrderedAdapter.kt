package com.example.shopsavvycommvp.ui.detailorder.view

import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopsavvycommvp.R
import com.example.shopsavvycommvp.data.network.request.Cart
import com.example.shopsavvycommvp.util.extension.loadImg
import kotlinx.android.synthetic.main.item_detail_order.view.*

class ListProductOrderedAdapter() : RecyclerView.Adapter<ListProductOrderedAdapter.viewholder>() {
    private var data = arrayListOf<Cart>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return LayoutInflater.from(parent.context).run {
            viewholder(inflate(R.layout.item_detail_order, parent, false))
        }
    }

    fun addData(list: ArrayList<Cart>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.bind(data[position])
    }

    class viewholder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        fun bind(cart: Cart) {
            itemView.imgItemDetailOrder.loadImg(cart.product?.dataimage?.get(0))
            itemView.tvItemDetailOrderName.text = cart.product?.name
            itemView.tvItemDetailOrderSoLuong.text = "Số lượng: ${cart.total}"
            itemView.tvItemDetailOrderGia.text = "$${cart.product?.price?.times(cart.total)}"
        }
    }
}