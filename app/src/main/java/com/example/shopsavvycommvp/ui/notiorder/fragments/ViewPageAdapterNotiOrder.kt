package com.example.shopsavvycommvp.ui.notiorder.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.shopsavvycommvp.ui.notiorder.fragments.cholayhang.view.ChoLayHangFragment
import com.example.shopsavvycommvp.ui.notiorder.fragments.choxacnhan.view.ChoXacNhanFragment
import com.example.shopsavvycommvp.ui.notiorder.fragments.dagiao.view.DaGiaoFragment
import com.example.shopsavvycommvp.ui.notiorder.fragments.dahuy.view.DaHuyFragment
import com.example.shopsavvycommvp.ui.notiorder.fragments.danggiao.view.DangGiaoFragment
import com.example.shopsavvycommvp.ui.notiorder.fragments.trahang.view.TraHangFragment

class ViewPageAdapterNotiOrder(val fm: FragmentManager,val number: Int):
FragmentPagerAdapter(fm){

    private var numbers: Int? = null
    init {
        this.numbers = number
    }
    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null

        when (position) {
            0 -> fragment = DangGiaoFragment()
            1 -> fragment = DaGiaoFragment()
            2 -> fragment = ChoLayHangFragment()
            3 -> fragment = ChoXacNhanFragment()
            4 -> fragment = DaHuyFragment()
            5 -> fragment = TraHangFragment()
        }
        return fragment!!
    }

    override fun getCount(): Int {
        return this.numbers!!
    }
    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> return "Đang giao"
            1 -> return "Đã giao"
            2 -> return "Chờ lấy hàng"
            3 -> return "Chờ xác nhận"
            4 -> return "Đã hủy"
            5 -> return "Trả hàng"
        }
        return "Đang giao"

    }
}