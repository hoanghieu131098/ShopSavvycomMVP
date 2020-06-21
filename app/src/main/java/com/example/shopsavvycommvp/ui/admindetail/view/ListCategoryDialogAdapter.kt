package com.example.shopsavvycommvp.ui.admindetail.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopsavvycommvp.R
import com.example.shopsavvycommvp.data.network.response.Category
import kotlinx.android.synthetic.main.item_category_dialog_small.view.*

class ListCategoryDialogAdapter(var list: ArrayList<Category> = arrayListOf()): RecyclerView.Adapter<ListCategoryDialogAdapter.viewholder>() {

    private var onItemCategoryChoose: (Category) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(LayoutInflater.from(parent.context).inflate(R.layout.item_category_dialog_small, parent, false))
    }

    fun setOnItemCategoryChoose(onItemCategoryChoose: (Category) -> Unit) {
        this.onItemCategoryChoose = onItemCategoryChoose
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            onItemCategoryChoose(list[position])
        }
    }

    class viewholder(itemView: View): RecyclerView.ViewHolder(itemView) {
            fun bind(category: Category) {
                itemView.tvNameCategory.text = category.name
            }
    }

}