package com.example.shopsavvycommvp.ui.main.fragments.cartfragment.view

import android.content.Intent
import android.os.Bundle
import android.util.LayoutDirection
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.HORIZONTAL
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopsavvycommvp.R
import com.example.shopsavvycommvp.data.network.request.Cart
import com.example.shopsavvycommvp.data.network.request.Order
import com.example.shopsavvycommvp.ui.base.view.BaseAdapter
import com.example.shopsavvycommvp.ui.base.view.BaseFragment
import com.example.shopsavvycommvp.ui.main.fragments.cartfragment.presenter.CartFragmentPresenter
import com.example.shopsavvycommvp.ui.main.fragments.fashionfragment.view.FashionFragmentMVPView
import com.example.shopsavvycommvp.ui.order.view.OrderActivity
import com.example.shopsavvycommvp.util.AppConstants
import com.example.shopsavvycommvp.util.ToastUtils
import kotlinx.android.synthetic.main.fragment_cart.*
import javax.inject.Inject

class CartFragment : BaseFragment(), CartFragmentMVPView, CartAdapter.onClickListenerItemCart {
    override fun onToTalProductItem(total: Int, id: Int) {
        presenter.getToTalProductItem(total, id)
    }

    override fun onClickDeleteItemCart(id: Int) {
        presenter.getClickDeleteItemCart(id)
    }

    override val layoutId: Int
        get() = R.layout.fragment_cart

    @Inject
    lateinit var presenter: CartFragmentPresenter
    private lateinit var adapterCart: CartAdapter
    private var mdata: ArrayList<Cart> = arrayListOf()
    private var totalMoney: Double = 0.0
    override fun setUp() {
        presenter.onAttach(this)
        initView()
        setonClickLisener()
    }

    private fun setonClickLisener() {
        ln_order_cart.setOnClickListener {
            if(!mdata.isNullOrEmpty()){
                val intent: Intent = Intent(requireContext(),OrderActivity::class.java)
                val order = Order(null,"","",0,totalMoney,false,mdata)
                intent.putExtra(AppConstants.INTENT_DATA_TO_ORDER,order)
                startActivity(intent)
            }else{
                this.showError("You have no items in your shopping cart!")
            }

        }
    }

    private fun initView() {
        adapterCart = CartAdapter()
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recycle_Cart.setHasFixedSize(true)
        val d =  DividerItemDecoration(requireContext(), layoutManager.getOrientation())
        recycle_Cart.addItemDecoration(d);
        recycle_Cart.adapter = adapterCart
        adapterCart.setClickLisenerItemCart(this@CartFragment)

    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun onResponse(data: ArrayList<Cart>) {
        if (data.size == 0) {
            tvNoResult.visibility = View.VISIBLE
            lnOrder.visibility = View.GONE
        } else {
            tvNoResult.visibility = View.GONE
            lnOrder.visibility = View.VISIBLE
        }
        mdata.clear()
        mdata.addAll(data)
        //totalmoney
        totalMoney = 0.0
        for(i in mdata.indices){
            totalMoney +=  mdata[i].total * mdata[i].product?.price!!
        }
        tv_total_cart.text = "TOTAL: $${totalMoney}"
        adapterCart.addData(data)
    }

    override fun onFailed(msg: String) {
        this.showError(msg)
    }

    override fun onResponseUpdatetotalProductCart(msg: String) {
    }

    override fun onResponseRemoveProductCart(msg: String) {
        ToastUtils.showToast(requireContext(), msg)
    }
}