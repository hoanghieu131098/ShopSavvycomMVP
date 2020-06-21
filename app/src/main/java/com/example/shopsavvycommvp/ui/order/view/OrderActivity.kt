package com.example.shopsavvycommvp.ui.order.view

import android.content.Intent
import android.util.Log
import com.example.shopsavvycommvp.R
import com.example.shopsavvycommvp.data.network.request.Cart
import com.example.shopsavvycommvp.data.network.request.Order
import com.example.shopsavvycommvp.data.network.response.Product
import com.example.shopsavvycommvp.ui.base.view.BaseActivity
import com.example.shopsavvycommvp.ui.main.activities.view.MainActivity
import com.example.shopsavvycommvp.ui.order.interactor.OrderMVPInteractor
import com.example.shopsavvycommvp.ui.order.presenter.OrderMVPPresenter
import com.example.shopsavvycommvp.util.AppConstants
import com.example.shopsavvycommvp.util.ToastUtils
import com.example.shopsavvycommvp.util.extension.loadImg
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_order.*
import javax.inject.Inject

class OrderActivity : BaseActivity(), OrderMVPView {
    override fun onReponseRemoveCart(msg: String) {}

    override fun orderFailed(msg: String) {
        toat(msg)
    }

    override fun orderSuccess(msg: String) {
        toat(msg)
        finish()
    }

    override fun checkInforFail(msg: String) {
        this.showError(msg)
    }

    override fun checkInforSuccess(lastOrder: Order) {
        ed_phone_number.setText(lastOrder.phoneNumber)
        edt_diachi.setText(lastOrder.address)
    }

    override fun onBackPressed() {
        finish()
    }
    override val layoutId: Int
        get() = R.layout.activity_order

    @Inject
    lateinit var presenter: OrderMVPPresenter<OrderMVPView, OrderMVPInteractor>
    private var mdata: ArrayList<Cart> = arrayListOf()
    private var morder: Order ?= null
    private var totalMoney: Double = 0.0
    private lateinit var mAuth: FirebaseAuth
    private var mUser: FirebaseUser? = null
    override fun setUp() {
        presenter.onAttach(this)
        receiverData()
        mAuth = FirebaseAuth.getInstance()
        mUser = mAuth.currentUser
        mUser?.let { updateHeader(it) }
        setonClickLisener()
    }

    private fun checkInforUserLastOrder(userID: String) {
        presenter.checkInforUserOrder(userID)
    }

    private fun updateHeader(user: FirebaseUser) {
        img_Profile_Order.loadImg(user.photoUrl.toString())
        tv_Username_Profile_Order.text = user.displayName
        checkInforUserLastOrder(user.uid)
    }

    private fun setonClickLisener() {
        btn_order.setOnClickListener {
            val phoneNumber = ed_phone_number.text.toString()
            val address = edt_diachi.text.toString()
            if (phoneNumber.isEmpty() || address.isEmpty()) {
                ToastUtils.showToast(this, "You must enter full information!")
            } else {
                morder!!.phoneNumber = phoneNumber
                morder!!.address = address
                morder!!.dateTime = System.currentTimeMillis()
                presenter.getOrder(morder!!)
            }
        }
    }

    private fun receiverData() {
        if (intent != null) {
            morder = intent.getSerializableExtra(AppConstants.INTENT_DATA_TO_ORDER) as Order
            mdata = morder!!.listCart!!
            var s: String = ""
            for (i in mdata.indices) {
                s = s + "Sản phẩm ${i + 1}: ${mdata[i].product!!.name.toLowerCase()} .\n"
            }
            tv_list_Product.text = s
            tvTotalMoney.text = "TOTAL: $${morder?.totalMoney}"
        }
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}