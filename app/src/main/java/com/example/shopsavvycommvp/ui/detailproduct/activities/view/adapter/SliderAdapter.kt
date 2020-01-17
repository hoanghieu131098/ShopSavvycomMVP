package com.example.shopsavvycommvp.ui.detailproduct.activities.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopsavvycommvp.R

import com.smarteist.autoimageslider.SliderViewAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_slider.view.*


class SliderAdapter(val context: Context) : SliderViewAdapter<SliderAdapter.viewholder>(){
    private var mdata: ArrayList<String>? = null
    fun setData(mdata: ArrayList<String>){
        this.mdata = mdata
    }
    override fun onCreateViewHolder(parent: ViewGroup?): viewholder {
        return viewholder(LayoutInflater.from(context).inflate(R.layout.item_slider,parent,false))
    }

    override fun onBindViewHolder(viewHolder: viewholder?, position: Int) {
        viewHolder?.bind(mdata!![position])
    }

    override fun getCount(): Int {
        return if(mdata!=null)
                    mdata!!.size
               else 0
    }

    class viewholder( val itemview: View): SliderViewAdapter.ViewHolder(itemview){
          fun bind( image: String){
              Picasso.get().load(image).error(R.drawable.img_loading).into(itemview.img_item_detail)

          }
    }
}