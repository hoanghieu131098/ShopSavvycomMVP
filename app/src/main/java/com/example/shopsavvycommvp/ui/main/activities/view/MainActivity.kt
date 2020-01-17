package com.example.shopsavvycommvp.ui.main.activities.view

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.shopsavvycommvp.R
import com.example.shopsavvycommvp.ui.base.view.BaseActivity
import com.example.shopsavvycommvp.ui.main.activities.interactor.MainMVPInteractor
import com.example.shopsavvycommvp.ui.main.activities.presenter.MainMVPPresenter
import com.example.shopsavvycommvp.ui.main.fragments.cartfragment.view.CartFragment
import com.example.shopsavvycommvp.ui.main.fragments.fashionfragment.view.FashionFragment
import com.example.shopsavvycommvp.ui.main.fragments.homefragment.view.HomeFragment
import com.example.shopsavvycommvp.ui.main.fragments.userfragment.view.UserFragment
import com.example.shopsavvycommvp.widget.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Collections.reverse
import javax.inject.Inject

class MainActivity : BaseActivity(), MainMVPView, HasSupportFragmentInjector {


    override val layoutId: Int
        get() = R.layout.activity_main


    @Inject
    internal lateinit var presenter: MainMVPPresenter<MainMVPView, MainMVPInteractor>
    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private val homeFragment: HomeFragment = HomeFragment()
    private val fashionFragment: FashionFragment = FashionFragment()
    private val cartFragment: CartFragment = CartFragment()
    private val userFragment: UserFragment = UserFragment()
    private val listFragment: MutableList<Fragment> = mutableListOf(
        homeFragment, fashionFragment, cartFragment, userFragment
    )

    private fun initview() {
        addFragment(listFragment, R.id.fragment_container)
        bottom_navigation_view.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    fun selectFm(id: Int) {
        bottom_navigation_view.selectedItemId = id
    }

    override fun setUp() {
        presenter.onAttach(this)
//        setupBottomNavigationBar()
        initview()
//       if(checkPalindrome("abba")){
//           Log.d("a","ok")
//       }
    }
    fun checkPalindrome(inputString: String): Boolean {
        val reverse = StringBuffer(inputString)
        if(reverse.toString().contentEquals(inputString))
            return true
        return false
    }
    override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    private val mOnNavigationItemSelectedListener =
        object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.tab_home -> {
                        showFragment(listFragment[0])
                    }
                    R.id.tab_fashion -> {
                        showFragment(listFragment[1])
                    }
                    R.id.tab_cart -> {
                        showFragment(listFragment[2])
                    }
                    R.id.tab_user -> {
                        showFragment(listFragment[3])
                    }
                }
                return true
            }

        }

    private fun setupBottomNavigationBar() {
        val navGraphIds = listOf(
            R.navigation.navigation_home,
            R.navigation.navigation_fashion,
            R.navigation.navigation_cart,
            R.navigation.navigation_profile
        )

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = bottom_navigation_view.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.fragment_container,
            intent = intent
        )


        navData = controller
    }

}
