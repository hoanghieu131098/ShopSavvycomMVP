package com.example.shopsavvycommvp.ui.main.fragments.homefragment.view

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopsavvycommvp.R
import com.example.shopsavvycommvp.data.network.response.Product
import com.example.shopsavvycommvp.util.AppConstants
import com.example.shopsavvycommvp.util.extension.loadImg
import kotlinx.android.synthetic.main.item_clothes_home.view.*


class ProductAllAdapter(var type: String) :
    RecyclerView.Adapter<ProductAllAdapter.ProductAllViewHolder>() {

    private var data: ArrayList<Product> = arrayListOf()
    private var dataBackUp: ArrayList<Product> = arrayListOf()
    private var onClickListener: ClickItemListener? = null

    fun setOnClickListener(listener: ClickItemListener) {
        onClickListener = listener
    }

    fun clearData() {
        data.clear()
        dataBackUp.clear()
        notifyDataSetChanged()
    }

    fun addData(mData: ArrayList<Product>) {
        data.addAll(mData)
        dataBackUp.addAll(mData)
        notifyDataSetChanged()
    }

    fun searchProduct(key: String) {
        data.clear()
        if (key.isEmpty()) {
            data.addAll(dataBackUp)
        } else {
            data.addAll(dataBackUp.filter { it.name.toLowerCase().contains(key.toLowerCase()) })
        }
        onClickListener?.mOnNoResultListener(data.size == 0)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAllViewHolder {
        return LayoutInflater.from(parent.context).run {
            ProductAllViewHolder(inflate(R.layout.item_clothes_home, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ProductAllViewHolder, position: Int) {
        holder.bind(data.get(position), type)
        holder.itemView.setOnClickListener {
            onClickListener?.mOnItemClickListener(data.get(position), holder.itemView)
        }
        holder.itemView.imvThreeDot.setOnClickListener {
            onClickListener?.mOnThreeDotClickListener(data.get(position), holder.itemView)
        }
    }

    class ProductAllViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(product: Product, type: String) {
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
            itemView.img_clothes_home.loadImg(product.dataimage?.get(0))
            itemView.tv_clothes_name_item_home.text = product.name
            itemView.tv_clothes_price_item_home.text = "$${product.price}"
            if (type == AppConstants.TYPE_PRODUCT_ADMIN) {
                itemView.imvThreeDot.visibility = View.VISIBLE
            }
        }
    }

    interface ClickItemListener {
        fun mOnItemClickListener(product: Product, view: View)
        fun mOnNoResultListener(isNoResult: Boolean)
        fun mOnThreeDotClickListener(product: Product, view: View)
    }
}
