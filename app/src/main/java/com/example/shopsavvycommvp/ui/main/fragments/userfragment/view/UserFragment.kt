package com.example.shopsavvycommvp.ui.main.fragments.userfragment.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.shopsavvycommvp.R
import com.example.shopsavvycommvp.ui.base.view.BaseFragment
import com.example.shopsavvycommvp.ui.login.view.LoginActivity
import com.example.shopsavvycommvp.ui.main.activities.view.MainActivity
import com.example.shopsavvycommvp.ui.main.fragments.userfragment.presenter.UserFragmentPresenter
import com.example.shopsavvycommvp.ui.notiorder.activities.view.NotiOrderActivity
import com.example.shopsavvycommvp.util.extension.loadImg
import com.example.shopsavvycommvp.widget.click

import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController
import kotlinx.android.synthetic.main.fragment_user.*
import javax.inject.Inject


class UserFragment : BaseFragment(),UserFragmentMVPView {

    override val layoutId: Int
        get() = R.layout.fragment_user

    @Inject
    lateinit var presenter: UserFragmentPresenter
    private lateinit var mAuth: FirebaseAuth
    private  var mUser: FirebaseUser?=null

    override fun setUp() {
        presenter.onAttach(this)
        mAuth = FirebaseAuth.getInstance()
        mUser = mAuth.currentUser
        mUser?.let { updateHeader(it) }
        checkHideShowView()
        setonClickLisener()

    }

    private fun setonClickLisener() {
        item_profile_setup_account.setOnClickListener{
            mAuth.signOut()
            LoginManager.getInstance().logOut()
            openLoginActivity()
        }
        img_cart_profile.setOnClickListener {
            val act = activity as MainActivity
            act.selectFm(R.id.tab_cart)
        }

        //Don mua
        item_profile_purchar_order.setOnClickListener{
            startActivity(Intent(requireContext(),NotiOrderActivity::class.java))
        }
        item_profile_card_service.setOnClickListener {  }
        item_profile_favorited.setOnClickListener {  }
        item_profile_new_see.setOnClickListener {  }
        item_profile_wallet_shop.setOnClickListener {  }
        item_profile_xu_shop.setOnClickListener {  }
        item_profile_evaluate.setOnClickListener {  }
        item_profile_wallet_voucher.setOnClickListener {  }
        item_profile_help_center.click().subscribe {
            navController?.navigate(R.id.toHelpCentreFragment, null)
        }

    }
     private fun updateHeader(user: FirebaseUser) {
         profile_img_nguoidung.loadImg(user.photoUrl.toString())
        tv_username.text = user.displayName
        if(user.email!=null){
            tv_useremail.text = user.email
        }
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
    private fun openLoginActivity(){
        startActivity(Intent(requireContext(), LoginActivity::class.java))
        requireActivity().finish()
    }

    private fun checkHideShowView() {

    }
    
    override fun numberItemCart(number: Int?) {
        if (number == 0) {
            tv_Item_Count_Cart_User.visibility = View.INVISIBLE
        } else {
            tv_Item_Count_Cart_User.visibility = View.VISIBLE
            tv_Item_Count_Cart_User.text = number.toString()
        }
    }

}