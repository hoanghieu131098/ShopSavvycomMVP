package com.example.shopsavvycommvp.ui.main.fragments.homefragment.view

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopsavvycommvp.R
import com.example.shopsavvycommvp.data.network.response.BaseModel
import com.example.shopsavvycommvp.data.network.response.Category
import com.example.shopsavvycommvp.data.network.response.Product
import com.example.shopsavvycommvp.ui.base.view.BaseAdapter
import com.example.shopsavvycommvp.ui.base.view.BaseFragment
import com.example.shopsavvycommvp.ui.detailcategory.view.DetailCategoryActivity
import com.example.shopsavvycommvp.ui.detailproduct.activities.view.DetailActivity
import com.example.shopsavvycommvp.ui.main.fragments.homefragment.presenter.HomeFragmentMVPPresenter
import com.example.shopsavvycommvp.ui.notiorder.activities.view.NotiOrderActivity
import com.example.shopsavvycommvp.util.AppConstants
import com.jakewharton.rxbinding.support.v7.widget.RxSearchView
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.toolbar_home.view.*
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import javax.inject.Inject


class HomeFragment : BaseFragment(), HomeFragmentMVPView, BaseAdapter.onBaseClickListener, ProductAllAdapter.ClickItemListener{

    override val layoutId: Int
        get() = R.layout.fragment_home

    @Inject
    lateinit var presenter: HomeFragmentMVPPresenter
    private lateinit var adapterProduct: ProductAllAdapter
    private lateinit var adapterCategory: BaseAdapter<Category>

    override fun setUp() {
        presenter.onAttach(this)
        presenter.getCategory()
        initView()
        setonClickLisener()
    }

    private fun initView() {
        //set event recycleview product all
        adapterProduct = ProductAllAdapter(AppConstants.TYPE_PRODUCT_HOME)
        adapterProduct.setOnClickListener(this)
        val layoutManager = GridLayoutManager(requireContext(), 2)
        recycal_list_item_fm_home.layoutManager = layoutManager
        recycal_list_item_fm_home.adapter = adapterProduct
        presenter.getProductToRcv()

        //set event recycleview category
        adapterCategory = BaseAdapter(requireContext(), R.layout.item_category_home_fm)
        val layoutManager2 = GridLayoutManager(
            requireContext(), 2,
            LinearLayoutManager.HORIZONTAL, false
        )
        recycle_category_home_fm.setHasFixedSize(true)
        recycle_category_home_fm.layoutManager = layoutManager2
        recycle_category_home_fm.adapter = adapterCategory
        adapterCategory.setBaseClickListener(this@HomeFragment)

    }

    override fun onItemBaseClick(T: BaseModel) {
        when (T) {
            is Category -> {
                val inten: Intent = Intent(requireContext(), DetailCategoryActivity::class.java)
                inten.putExtra(AppConstants.INTENT_ITEM_TO_DETAIL_CATEGORY, T)
                startActivity(inten)
            }
        }
    }

    private fun setonClickLisener() {
        ln_tb_home.btnNotifiOrder.setOnClickListener {
            startActivity(Intent(requireContext(), NotiOrderActivity::class.java))
        }

       RxSearchView.queryTextChanges(ln_tb_home.edtTbHomeSearchProduct)
           .skip(1)
           .observeOn(AndroidSchedulers.mainThread())
           .subscribe(object : Observer<CharSequence?> {
               override fun onError(e: Throwable?) {
               }
               override fun onNext(t: CharSequence?) {
                   adapterProduct.searchProduct(t.toString())
               }
               override fun onCompleted() {
               }
           })
    }

    override fun getProductAllFailed(msg: String) {
        this.showError(msg)
    }

    override fun getProductAllSuccess(data: ArrayList<Product>) {
        adapterProduct.clearData()
        adapterProduct.addData(data)
    }

    override fun getCategoryAllFailed(msg: String) {
        this.showError(msg)
    }

    override fun getCategoryAllSuccess(data: List<Category>) {
        adapterCategory.setData(data as ArrayList<Category>)
    }

    override fun onReponseTaTolOrderFailed(msg: String) {
        this.showError(msg)
    }

    override fun onReponseTaTolOrderSuccess(total: Int) {
        //set total noti
        if (total > 0) {
            ln_tb_home.tv_Item_Count_Notifi_Order.visibility = View.VISIBLE
            ln_tb_home.tv_Item_Count_Notifi_Order.text = total.toString()
        } else {
            ln_tb_home.tv_Item_Count_Notifi_Order.visibility = View.INVISIBLE
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

    override fun mOnItemClickListener(product: Product, view: View) {
        val intent = Intent(requireContext(), DetailActivity::class.java)
        intent.putExtra(AppConstants.INTENT_ITEM_TO_DETAIL, product)
        startActivity(intent)
    }

    override fun mOnNoResultListener(isNoResult: Boolean) {
        if (isNoResult) {
            tvNoResult.visibility = View.VISIBLE
        } else {
            tvNoResult.visibility = View.GONE
        }
    }

    override fun mOnThreeDotClickListener(product: Product, view: View) {}

}