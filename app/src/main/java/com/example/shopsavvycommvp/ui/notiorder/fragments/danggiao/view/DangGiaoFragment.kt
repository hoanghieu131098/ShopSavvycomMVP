package com.example.shopsavvycommvp.ui.notiorder.fragments.danggiao.view

import android.content.Intent
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopsavvycommvp.R
import com.example.shopsavvycommvp.data.network.request.Order
import com.example.shopsavvycommvp.data.network.response.BaseModel
import com.example.shopsavvycommvp.ui.base.view.BaseAdapter
import com.example.shopsavvycommvp.ui.base.view.BaseFragment
import com.example.shopsavvycommvp.ui.detailorder.view.DetailOrderActivity
import com.example.shopsavvycommvp.ui.notiorder.fragments.danggiao.presenter.DangGiaoMVPPresenter
import com.example.shopsavvycommvp.util.AppConstants
import com.example.shopsavvycommvp.util.ToastUtils
import kotlinx.android.synthetic.main.fragment_danggiao.*
import javax.inject.Inject

class DangGiaoFragment : BaseFragment(), DangGiaoMVPView, BaseAdapter.onBaseClickListener {


    override val layoutId: Int
        get() = R.layout.fragment_danggiao
    @Inject
    lateinit var presenter: DangGiaoMVPPresenter
    private lateinit var mAdapterOrder: BaseAdapter<Order>
    override fun setUp() {
        presenter.onAttach(this)
        initView()
        setonClickLisener()
    }

    private fun setonClickLisener() {

    }

    private fun initView() {
        mAdapterOrder = BaseAdapter(requireContext(), R.layout.item_order)
        recycleViewDangGiaoOrder.setHasFixedSize(true)
        recycleViewDangGiaoOrder.adapter = mAdapterOrder
        mAdapterOrder.setBaseClickListener(this@DangGiaoFragment)

}

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun onReponseFailed(msg: String) {
        ToastUtils.showToast(requireContext(), msg)
    }

    override fun onReponseSuccess(data: ArrayList<Order>) {
        data.let {
            mAdapterOrder.setData(data)
        }
    }

    override fun onItemBaseClick(T: BaseModel) {
        when (T) {
            is Order -> {
                if(!T.active){
                    T.id?.let { presenter.doUpdateActive(it) }
                }
                //send id order to activity detailorder
                val inten: Intent = Intent(requireContext(),DetailOrderActivity::class.java)
                inten.putExtra(AppConstants.DangGiao.INTENT_ID_ORDER,T.id)
                startActivity(inten)
            }
        }
    }
    override fun onReponseUpdateActive(msg: String) {

    }

}