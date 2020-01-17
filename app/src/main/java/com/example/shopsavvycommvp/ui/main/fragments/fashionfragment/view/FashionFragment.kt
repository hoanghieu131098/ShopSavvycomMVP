package com.example.shopsavvycommvp.ui.main.fragments.fashionfragment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.shopsavvycommvp.R
import com.example.shopsavvycommvp.ui.base.view.BaseFragment
import com.example.shopsavvycommvp.ui.main.fragments.fashionfragment.presenter.FashionFragmentPresenter
import javax.inject.Inject


class FashionFragment : BaseFragment(),FashionFragmentMVPView{
    override val layoutId: Int
        get() = R.layout.fragment_fashion
  @Inject
  lateinit var presenter: FashionFragmentPresenter
    override fun setUp() {
        presenter.onAttach(this)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }


}