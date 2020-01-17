package com.example.shopsavvycommvp.ui.detailproduct.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.shopsavvycommvp.ui.detailproduct.fragments.inforfragment.view.InforFragment
import com.example.shopsavvycommvp.ui.detailproduct.fragments.ratingfragment.view.RatingFragment

class ViewPagerAdapter(val fm: FragmentManager, val number: Int) :
    FragmentPagerAdapter(fm) {
    private var numbers: Int? =null

    init {
        this.numbers = number
    }

    override fun getPageTitle(position: Int): CharSequence? {
        if(position==0){
            return "INFOMATION"
        }else{
            return "VOTE"
        }

    }

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null

        when (position) {
            0 -> fragment = InforFragment()
            1 -> fragment = RatingFragment()
        }
        return fragment!!
    }

    override fun getCount(): Int {
        return this.numbers!!

    }
}