package com.example.shopsavvycommvp.ui.notiorder.activities.view

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import com.example.shopsavvycommvp.R
import com.example.shopsavvycommvp.ui.base.view.BaseActivity
import com.example.shopsavvycommvp.ui.detailproduct.fragments.ViewPagerAdapter
import com.example.shopsavvycommvp.ui.notiorder.activities.interactor.NotiOrderMVPInteractor
import com.example.shopsavvycommvp.ui.notiorder.activities.presenter.NotiOrderMVPPresenter
import com.example.shopsavvycommvp.ui.notiorder.fragments.ViewPageAdapterNotiOrder
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_noti_order.*
import javax.inject.Inject


class NotiOrderActivity: BaseActivity(),NotiOrderMVPView, HasSupportFragmentInjector {


    override val layoutId: Int
        get() = R.layout.activity_noti_order

    @Inject
    lateinit var presenter: NotiOrderMVPPresenter<NotiOrderMVPView,NotiOrderMVPInteractor>
    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector
    override fun setUp() {
        //set Tablayout : infor and rate
        val viewpager = ViewPageAdapterNotiOrder(supportFragmentManager,6)
        viewpagerNotiOrder.adapter = viewpager
        tablayoutNotiOrder.setupWithViewPager(viewpagerNotiOrder)
        presenter.onAttach(this)
        setonClickLisener()
    }

    private fun setonClickLisener() {
        imgTbNotiOrderBack.setOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}