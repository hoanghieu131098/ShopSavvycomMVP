package com.example.shopsavvycommvp.ui.detailcategory.view

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shopsavvycommvp.R
import com.example.shopsavvycommvp.data.network.response.BaseModel
import com.example.shopsavvycommvp.data.network.response.Category
import com.example.shopsavvycommvp.data.network.response.Product
import com.example.shopsavvycommvp.ui.base.view.BaseActivity
import com.example.shopsavvycommvp.ui.base.view.BaseAdapter
import com.example.shopsavvycommvp.ui.detailcategory.interactor.DetailCategoryMVPInteractor
import com.example.shopsavvycommvp.ui.detailcategory.presenter.DetailCategoryPresenter
import com.example.shopsavvycommvp.ui.detailproduct.activities.interactor.DetailProductMVPInteractor
import com.example.shopsavvycommvp.ui.detailproduct.activities.view.DetailActivity
import com.example.shopsavvycommvp.ui.detailproduct.activities.view.adapter.SliderAdapter
import com.example.shopsavvycommvp.util.AppConstants


import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.activity_detail_category.*
import javax.inject.Inject

class DetailCategoryActivity: BaseActivity(),DetailCategoryMVPView ,BaseAdapter.onBaseClickListener{
    override fun onItemBaseClick(T: BaseModel) {
        val inten: Intent = Intent(this, DetailActivity::class.java)
        inten.putExtra(AppConstants.INTENT_ITEM_TO_DETAIL,T as Product )
        startActivity(inten)
    }

    override val layoutId: Int
        get() = R.layout.activity_detail_category

    @Inject
    internal lateinit var presenter: DetailCategoryPresenter<DetailCategoryMVPView,DetailCategoryMVPInteractor>
    private var mCategory: Category?= null
    private lateinit var adapter: BaseAdapter<Product>

    override fun setUp() {
        presenter.onAttach(this)
        receivedatafromHome()
        setSlider(mCategory)
        addDataView()
        //set event recycleview product all
        adapter = BaseAdapter(this, R.layout.item_clothes_home)
        recycle_detail_category.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(this, 2)
        recycle_detail_category.layoutManager = layoutManager
        recycle_detail_category.adapter = adapter
        adapter.setBaseClickListener(this@DetailCategoryActivity)

        presenter.getKeyProductAll(mCategory!!.id.toString())

        //event click back
        img_tb_back_detail_category.setOnClickListener {
            finish()
        }
    }

    private fun addDataView() {
        tv_tb_title_product_detail_category.text = this.mCategory?.name
    }

    private fun setSlider(mCategory: Category?) {
        var mslideradapter = SliderAdapter(this)
        val data: ArrayList<String> = arrayListOf()
        data.add(mCategory!!.image)
        data.add("http://images.complex.com/complex/image/upload/c_fill,g_center,w_1200/fl_lossy,q_70/nacqv6y9ixfynv9zskuu.gif")
        mslideradapter.setData(data)
        imageSlider_detail_category.sliderAdapter = mslideradapter
        imageSlider_detail_category.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        imageSlider_detail_category.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        imageSlider_detail_category.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        imageSlider_detail_category.setScrollTimeInSec(4); //set scroll delay in seconds :
        imageSlider_detail_category.startAutoCycle();
    }

    private fun receivedatafromHome() {
        mCategory = intent.getSerializableExtra(AppConstants.INTENT_ITEM_TO_DETAIL_CATEGORY) as Category
    }

    override fun getProductAllFailed(msg: String) {
//        showAlert(msg)
    }

    override fun getProductAllSuccess(data: List<Product>) {
        if(data.isEmpty()){
            tvEmptyProductDetailCategory.visibility = View.VISIBLE
            Log.d("Text","Empty")
        }else{
            Log.d("Text"," not Empty")
            adapter.setData(data as ArrayList<Product>)
        }
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

}