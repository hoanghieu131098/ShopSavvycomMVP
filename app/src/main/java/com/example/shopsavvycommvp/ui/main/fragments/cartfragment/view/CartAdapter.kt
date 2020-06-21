package com.example.shopsavvycommvp.ui.main.fragments.cartfragment.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopsavvycommvp.R
import com.example.shopsavvycommvp.data.network.request.Cart
import com.example.shopsavvycommvp.ui.detailorder.view.ListProductOrderedAdapter
import com.example.shopsavvycommvp.util.extension.loadImg
import kotlinx.android.synthetic.main.item_product_cart.view.*

class CartAdapter() : RecyclerView.Adapter<CartAdapter.viewholder>() {
    private var data = arrayListOf<Cart>()
    private var mListenerCart: onClickListenerItemCart? = null

    fun setClickLisenerItemCart(mListenerCart: onClickListenerItemCart) {
        this.mListenerCart = mListenerCart
    }
    fun addData(list: ArrayList<Cart>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return LayoutInflater.from(parent.context).run {
            viewholder(inflate(R.layout.item_product_cart, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val cart = data[position]
        holder.bind(cart)
        holder.itemView.img_item_minus_cart.setOnClickListener {
            if (holder.itemView.tv_item_number_cart.text.toString().toInt() > 1) {
                holder.itemView.tv_item_number_cart.text =
                    (holder.itemView.tv_item_number_cart.text.toString()
                        .toInt() - 1).toString()
                cart.product?.id?.toInt()?.let { it1 ->
                    mListenerCart?.onToTalProductItem(
                        holder.itemView.tv_item_number_cart.text.toString().toInt(),
                        it1
                    )

                }
            }
        }
        holder.itemView.img_item_plus_cart.setOnClickListener {
            holder.itemView.tv_item_number_cart.text =
                (holder.itemView.tv_item_number_cart.text.toString().toInt() + 1).toString()
            mListenerCart?.onToTalProductItem(
                holder.itemView.tv_item_number_cart.text.toString().toInt(),
                cart.product!!.id.toInt()
            )

        }
        holder.itemView.img_item_delete_cart.setOnClickListener {
            cart.product?.id?.let { it1 -> mListenerCart?.onClickDeleteItemCart(it1) }
        }
    }

    class viewholder(item: View) : RecyclerView.ViewHolder(item) {
        fun bind(cart: Cart) {
            itemView.img_item_product_cart.loadImg(cart.product?.dataimage?.get(0))
            itemView.tv_item_name_cart.text = cart.product?.name
            itemView.tv_item_price_cart.text = "$ ${cart.product?.price}"
            itemView.tv_item_number_cart.text = cart.total.toString()
        }
    }

    interface onClickListenerItemCart {
        fun onToTalProductItem(total: Int, id: Int)
        fun onClickDeleteItemCart(id: Int)
    }
}