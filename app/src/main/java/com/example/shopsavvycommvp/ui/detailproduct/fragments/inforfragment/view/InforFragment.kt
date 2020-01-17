package com.example.shopsavvycommvp.ui.detailproduct.fragments.inforfragment.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

import com.example.shopsavvycommvp.R
import com.example.shopsavvycommvp.data.network.response.Product
import com.example.shopsavvycommvp.ui.base.view.BaseFragment
import com.example.shopsavvycommvp.ui.detailproduct.activities.view.DetailActivity
import kotlinx.android.synthetic.main.fragment_infor.*




class InforFragment : BaseFragment(),InforFragmentMVPView {
    override val layoutId: Int
        get() = R.layout.fragment_infor
    private lateinit var act: DetailActivity


    override fun setUp() {
        getInforFromDetail()
    }

    private fun getInforFromDetail() {
        act = activity as DetailActivity
        act.getDescription().observe(act, object : Observer<Product?> {
            override fun onChanged(t: Product?) {
                tv_inforDetail.text = t?.description
            }
        })
    }







}