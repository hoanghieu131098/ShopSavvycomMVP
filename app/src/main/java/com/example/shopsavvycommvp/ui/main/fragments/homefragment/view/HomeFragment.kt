package com.example.shopsavvycommvp.ui.main.fragments.homefragment.view

import android.annotation.SuppressLint
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View

import kotlinx.android.synthetic.main.fragment_home.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import android.widget.ProgressBar
import com.example.shopsavvycommvp.R
import com.example.shopsavvycommvp.data.network.response.BaseModel
import com.example.shopsavvycommvp.data.network.response.Category
import com.example.shopsavvycommvp.data.network.response.Product
import com.example.shopsavvycommvp.ui.base.view.BaseAdapter
import com.example.shopsavvycommvp.ui.base.view.BaseFragment
import com.example.shopsavvycommvp.ui.detailcategory.view.DetailCategoryActivity
import com.example.shopsavvycommvp.ui.detailproduct.activities.view.DetailActivity
import com.example.shopsavvycommvp.ui.main.fragments.homefragment.interactor.HomeFragmentMVPInteractor
import com.example.shopsavvycommvp.ui.main.fragments.homefragment.presenter.HomeFragmentPresenter
import com.example.shopsavvycommvp.ui.notiorder.activities.view.NotiOrderActivity
import com.example.shopsavvycommvp.util.AppConstants
import com.example.shopsavvycommvp.util.ToastUtils

import com.github.ybq.android.spinkit.style.Circle
import com.jakewharton.rxbinding.support.v7.widget.RxSearchView
import com.jakewharton.rxbinding3.widget.textChangeEvents
import kotlinx.android.synthetic.main.toolbar_home.view.*
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.collections.ArrayList


class HomeFragment : BaseFragment(),HomeFragmentMVPView,BaseAdapter.onBaseClickListener  {



    override val layoutId: Int
        get() = R.layout.fragment_home

    @Inject
    lateinit var presenter: HomeFragmentPresenter
    private lateinit var adapterproduct: BaseAdapter<Product>
    private lateinit var adapterCategory: BaseAdapter<Category>
    private var mdata: ArrayList<Product> = arrayListOf()
    override fun setUp() {
        presenter.onAttach(this)
        initView()
        setonClickLisener()
    }

    private fun initView() {
        Log.d("testCate", "home")
        //set event recycleview product all
        adapterproduct = BaseAdapter(requireContext(), R.layout.item_clothes_home)
        recycal_list_item_fm_home.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(requireContext(), 2)
        recycal_list_item_fm_home.layoutManager = layoutManager
        recycal_list_item_fm_home.adapter = adapterproduct
        adapterproduct.setBaseClickListener(this@HomeFragment)

        //set event recycleview category
        adapterCategory = BaseAdapter(requireContext(),R.layout.item_category_home_fm)
        val layoutManager2 = GridLayoutManager(
            requireContext(), 2,
            LinearLayoutManager.HORIZONTAL, false
        )
        recycle_category_home_fm.setHasFixedSize(true)
        recycle_category_home_fm.layoutManager = layoutManager2
        recycle_category_home_fm.adapter = adapterCategory
        adapterCategory.setBaseClickListener(this@HomeFragment)
        presenter.getCategory()
        presenter.getKeyProductAll("all")
    }

    override fun onItemBaseClick(T: BaseModel) {
        when(T) {
            is Product -> {
                val inten: Intent = Intent(requireContext(), DetailActivity::class.java)
                inten.putExtra(AppConstants.INTENT_ITEM_TO_DETAIL,T as Product )
                startActivity(inten)
            }
            is Category -> {
                val inten: Intent = Intent(requireContext(), DetailCategoryActivity::class.java)
                inten.putExtra(AppConstants.INTENT_ITEM_TO_DETAIL_CATEGORY,T as Category )
                startActivity(inten)
            }
        }
    }
    private fun setonClickLisener() {
       ln_tb_home.btnNotifiOrder.setOnClickListener {
           startActivity(Intent(requireContext(),NotiOrderActivity::class.java))
       }
       RxSearchView.queryTextChanges(ln_tb_home.edtTbHomeSearchProduct)
           .skip(1)
           .debounce(1500, TimeUnit.MILLISECONDS)
           .observeOn(AndroidSchedulers.mainThread())
           .subscribe(object : Observer<CharSequence?> {
               override fun onError(e: Throwable?) {

               }
               override fun onNext(t: CharSequence?) {
                   Log.d("search",t.toString())
                   presenter.setSearchProduct(t.toString())
               }

               override fun onCompleted() {
               }
           })
    }

    @SuppressLint("LogNotTimber")
    override fun getProductAllFailed(msg: String) {
        Log.d("recycle ","Failed product")

    }


    override fun getProductAllSuccess(data: List<Product>) {
        mdata = data as ArrayList<Product>
        Log.d("mdata ","hhhhh"+ data.size)
        adapterproduct.setData(data)
    }

    @SuppressLint("LogNotTimber")
    override fun getCategoryAllFailed(msg: String) {
        Log.d("recycle ","Failed category")
    }

    @SuppressLint("LogNotTimber")
    override fun getCategoryAllSuccess(data: List<Category>) {
        Log.d("recycle ","success category")
        adapterCategory.setData(data as ArrayList<Category>)
    }

    override fun onReponseTaTolOrderFailed(msg: String) {

    }

    override fun onReponseTaTolOrderSuccess(total: Int) {
        //set total noti
        if(total>0){
            ln_tb_home.tv_Item_Count_Notifi_Order.visibility = View.VISIBLE
            ln_tb_home.tv_Item_Count_Notifi_Order.text = total.toString()
        }else{
            ln_tb_home.tv_Item_Count_Notifi_Order.visibility = View.INVISIBLE
        }
    }


    override fun onReponseSearchProductFailed(msg: String) {

    }

    override fun onReponseSearchProductSuccess(data: ArrayList<Product>?) {
        Log.d("mdata ",""+ mdata.size)
        if(!data!!.isEmpty()){
            adapterproduct.setData(data)
        }else{
            adapterproduct.setData(mdata)
        }
    }

    override fun onDetach() {
        presenter.onDetach()
        super.onDetach()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

}