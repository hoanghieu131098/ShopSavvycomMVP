package com.example.shopsavvycommvp.ui.detailorder.view

import com.example.shopsavvycommvp.R
import com.example.shopsavvycommvp.data.network.request.Order
import com.example.shopsavvycommvp.ui.base.view.BaseActivity
import com.example.shopsavvycommvp.ui.detailorder.interactor.DetailOrderMVPInteractor
import com.example.shopsavvycommvp.ui.detailorder.presenter.DetailOrderMVPPresenter
import com.example.shopsavvycommvp.util.AppConstants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_detail_order.*
import java.text.SimpleDateFormat
import javax.inject.Inject

class DetailOrderActivity : BaseActivity(), DetailOrderMVPView {
    override val layoutId: Int
        get() = R.layout.activity_detail_order

    @Inject
    lateinit var presenter: DetailOrderMVPPresenter<DetailOrderMVPView, DetailOrderMVPInteractor>
    private var mOrder: Order? = null
    private lateinit var mAdapterItemCart: ListProductOrderedAdapter
    private var mUser: FirebaseUser? = null
    override fun setUp() {
        presenter.onAttach(this)
        getDatafromDangGiao()
        setOnClicklisener()
    }

    private fun getDatafromDangGiao() {
        if (intent != null) {
            val idOrder = intent.getStringExtra(AppConstants.DangGiao.INTENT_ID_ORDER)
            presenter.setIdOrderLoadDetail(idOrder)
        }
    }

    private fun initView() {
        //init list
        mAdapterItemCart = ListProductOrderedAdapter()
        recycleDetailOrderItemCart.setHasFixedSize(true)
        recycleDetailOrderItemCart.adapter = mAdapterItemCart
        //set name customer
        mUser = FirebaseAuth.getInstance().currentUser
        if (mUser?.displayName?.isNotEmpty()!!) {
            tvDetailOrderNameCustomer.text = mUser?.displayName
        }
        //set data list
        mOrder?.listCart?.let { mAdapterItemCart.addData(it) }
        //set phone customer
        tvDetailOrderPhoneCustomer.text = mOrder?.phoneNumber
        //set address
        tvDetailOrderAddressCustomer.text = mOrder?.address
        //set total money
        tvDetailOrderToTalMoneyOrder.text = "$ ${mOrder?.totalMoney}"
        //set ID Order
        tvDetailOrderIdOrder.text = mOrder?.id
        //set Time order
        val date = SimpleDateFormat("dd-MM-yyyy HH:mm")
        tvDetailOrderTimeOrder.text = date.format(mOrder?.dateTime)
    }

    private fun setOnClicklisener() {
        imgTbDetailOrderBack.setOnClickListener {
            finish()
        }
    }

    override fun onReponseDetailOrder(order: Order) {
        mOrder = order
        initView()
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}