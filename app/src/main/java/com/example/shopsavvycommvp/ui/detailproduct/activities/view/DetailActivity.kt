package com.example.shopsavvycommvp.ui.detailproduct.activities.view

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide.init
import com.example.shopsavvycommvp.R
import com.example.shopsavvycommvp.data.network.request.Cart
import com.example.shopsavvycommvp.data.network.request.Order
import com.example.shopsavvycommvp.data.network.response.Product
import com.example.shopsavvycommvp.ui.base.presenter.MVPPresenter
import com.example.shopsavvycommvp.ui.base.view.BaseActivity
import com.example.shopsavvycommvp.ui.detailproduct.activities.interactor.DetailProductMVPInteractor
import com.example.shopsavvycommvp.ui.detailproduct.activities.presenter.DetailProductMVPPresenter
import com.example.shopsavvycommvp.ui.detailproduct.activities.presenter.DetailProductPresenter
import com.example.shopsavvycommvp.ui.detailproduct.activities.view.adapter.SliderAdapter
import com.example.shopsavvycommvp.ui.detailproduct.fragments.ViewPagerAdapter
import com.example.shopsavvycommvp.ui.detailproduct.fragments.ratingfragment.view.RatingFragment
import com.example.shopsavvycommvp.ui.order.view.OrderActivity
import com.example.shopsavvycommvp.util.AppConstants
import com.example.shopsavvycommvp.util.ToastUtils

import com.google.android.material.appbar.AppBarLayout
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : BaseActivity(), DetailProductMVPView, HasSupportFragmentInjector {


    override val layoutId: Int
        get() = R.layout.activity_detail

    @Inject
    internal lateinit var presenter: DetailProductMVPPresenter<DetailProductMVPView, DetailProductMVPInteractor>
    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector
    private var mProduct: Product? = null
    private lateinit var mslideradapter: SliderAdapter
    private var description = MutableLiveData<Product>()
    private var idproduct = MutableLiveData<String>()


    fun getDescription(): MutableLiveData<Product> {
        return description
    }

    fun getIdProduct(): MutableLiveData<String> {
        return idproduct
    }

    override fun setUp() {
        setUpView()
        //set Tablayout : infor and rate
        val viewpager = ViewPagerAdapter(supportFragmentManager, 2)
        viewpager_detail.adapter = viewpager
        tablayout_detail.setupWithViewPager(viewpager_detail)
        presenter.onAttach(this)
        receiveDatafromHome()
        iniView()
        setSlider()
        checkCollapsed()



    }

    private fun setUpView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }

    private fun checkCollapsed() {
        //event check collapsed
        app_bar_detail.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            override fun onOffsetChanged(appbar: AppBarLayout?, status: Int) {
                if (status == 0) {
                    tv_text.visibility = View.INVISIBLE
                } else if (Math.abs(status) >= appbar!!.totalScrollRange) {
                    tv_text.visibility = View.VISIBLE
                    tv_text.text = tv_clothes_name_detail.text

                }
            }
        })
    }

    private fun setSlider() {
        mslideradapter = SliderAdapter(this)
        mslideradapter.setData(mProduct!!.dataimage!!)
        imageSlider_detail.sliderAdapter = mslideradapter
        imageSlider_detail.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        imageSlider_detail.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        imageSlider_detail.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        imageSlider_detail.setIndicatorSelectedColor(Color.WHITE);
        imageSlider_detail.setIndicatorUnselectedColor(Color.GRAY);
        imageSlider_detail.setScrollTimeInSec(4); //set scroll delay in seconds :
        imageSlider_detail.startAutoCycle();
    }

    private fun iniView() {
        if (mProduct != null) {
            tv_clothes_name_detail.text = mProduct!!.name
            tv_clothes_price_detail.text = "$ ${mProduct!!.price}"

        }
        img_tb_back_detail.setOnClickListener {
            finish()
        }
        img_tb_cart_detail.setOnClickListener {
            presenter.updateCart(Cart(1, mProduct))
        }
        btn_buynow_detail.setOnClickListener {
            val intent: Intent = Intent(this, OrderActivity::class.java)
            val mdataCart: ArrayList<Cart> = arrayListOf()
            mdataCart.add(Cart(1, mProduct))
            val order = Order(null, "", "", 0, mProduct!!.price.toDouble(), false,mdataCart)
            intent.putExtra(AppConstants.INTENT_DATA_TO_ORDER, order)
            startActivity(intent)
            finish()
        }
    }

    private fun receiveDatafromHome() {
        val intent = intent
        mProduct = intent.getSerializableExtra(AppConstants.INTENT_ITEM_TO_DETAIL) as Product
        Log.d("testProduct", mProduct!!.id)
        description.postValue(mProduct)
        idproduct.postValue(mProduct!!.id)
    }

    override fun onResponse(msg: String) {
        toat(msg)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}
