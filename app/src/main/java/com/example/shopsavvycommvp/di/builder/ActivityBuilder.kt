package com.example.shopsavvycommvp.di.builder

import com.example.shopsavvycommvp.ui.admin.AdminActivityModule
import com.example.shopsavvycommvp.ui.admin.view.AdminActivity
import com.example.shopsavvycommvp.ui.admindetail.AdminDetailActivityModule
import com.example.shopsavvycommvp.ui.admindetail.view.AdminDetailActivity
import com.example.shopsavvycommvp.ui.detailcategory.DetailCategoryModule
import com.example.shopsavvycommvp.ui.detailcategory.view.DetailCategoryActivity
import com.example.shopsavvycommvp.ui.detailorder.DetailOrderActivityModule
import com.example.shopsavvycommvp.ui.detailorder.view.DetailOrderActivity
import com.example.shopsavvycommvp.ui.detailproduct.activities.DetailProductModule
import com.example.shopsavvycommvp.ui.detailproduct.activities.view.DetailActivity
import com.example.shopsavvycommvp.ui.detailproduct.fragments.inforfragment.InforFragmentProvider
import com.example.shopsavvycommvp.ui.detailproduct.fragments.ratingfragment.RatingFragmentProvider
import com.example.shopsavvycommvp.ui.login.LoginActivityModule
import com.example.shopsavvycommvp.ui.login.view.LoginActivity
import com.example.shopsavvycommvp.ui.main.activities.MainActivityModule
import com.example.shopsavvycommvp.ui.main.activities.view.MainActivity
import com.example.shopsavvycommvp.ui.main.fragments.cartfragment.CartFragmentProvider
import com.example.shopsavvycommvp.ui.main.fragments.fashionfragment.FashionFragmentProvider
import com.example.shopsavvycommvp.ui.main.fragments.homefragment.HomFragmentProvider
import com.example.shopsavvycommvp.ui.main.fragments.userfragment.UserFragmentProvider
import com.example.shopsavvycommvp.ui.notiorder.activities.NotiOrderActivityModule
import com.example.shopsavvycommvp.ui.notiorder.activities.view.NotiOrderActivity
import com.example.shopsavvycommvp.ui.notiorder.fragments.cholayhang.ChoLayHangFragmentProvider
import com.example.shopsavvycommvp.ui.notiorder.fragments.choxacnhan.ChoXacNhanFragmentProvider
import com.example.shopsavvycommvp.ui.notiorder.fragments.dagiao.DaGiaoFragmentProvider
import com.example.shopsavvycommvp.ui.notiorder.fragments.dahuy.DaHuyFragmentProvider
import com.example.shopsavvycommvp.ui.notiorder.fragments.danggiao.DangGiaoFragmentProvider
import com.example.shopsavvycommvp.ui.notiorder.fragments.trahang.TraHangFragmentProvider
import com.example.shopsavvycommvp.ui.order.OrderActivityModule
import com.example.shopsavvycommvp.ui.order.view.OrderActivity
import com.example.shopsavvycommvp.ui.splash.SplashActivityModule
import com.example.shopsavvycommvp.ui.splash.view.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(
        modules = [(MainActivityModule::class), (FashionFragmentProvider::class),
            (HomFragmentProvider::class), (CartFragmentProvider::class), (UserFragmentProvider::class)]
    )
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(
        modules = [(DetailProductModule::class), (InforFragmentProvider::class),
            (RatingFragmentProvider::class)]
    )
    abstract fun bindDetailActivity(): DetailActivity

    @ContributesAndroidInjector(modules = [(DetailCategoryModule::class)])
    abstract fun bindDetailCategoryActivity(): DetailCategoryActivity

    @ContributesAndroidInjector(modules = [(SplashActivityModule::class)])
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [(LoginActivityModule::class)])
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [(OrderActivityModule::class)])
    abstract fun bindOrderActivity(): OrderActivity

    @ContributesAndroidInjector(
        modules = [(NotiOrderActivityModule::class), (ChoLayHangFragmentProvider::class),
            (ChoXacNhanFragmentProvider::class), (DaGiaoFragmentProvider::class),
            (DaHuyFragmentProvider::class), (DangGiaoFragmentProvider::class), (TraHangFragmentProvider::class)]
    )
    abstract fun bindNotiOrderActivity(): NotiOrderActivity

    @ContributesAndroidInjector(modules = [(DetailOrderActivityModule::class)])
    abstract fun bindDetailOrderActivity(): DetailOrderActivity

    @ContributesAndroidInjector(modules = [(AdminActivityModule::class)])
    abstract fun bindAdminActivity(): AdminActivity

    @ContributesAndroidInjector(modules = [(AdminDetailActivityModule::class)])
    abstract fun bindAdminDetail(): AdminDetailActivity


}