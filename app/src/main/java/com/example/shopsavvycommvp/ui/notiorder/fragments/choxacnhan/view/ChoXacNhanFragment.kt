package com.example.shopsavvycommvp.ui.notiorder.fragments.choxacnhan.view

import com.example.shopsavvycommvp.R
import com.example.shopsavvycommvp.ui.base.view.BaseFragment
import com.example.shopsavvycommvp.ui.notiorder.fragments.choxacnhan.presenter.ChoXacNhanMVPPresenter
import javax.inject.Inject

class ChoXacNhanFragment : BaseFragment(),ChoXacNhanMVPView{
    override val layoutId: Int
        get() = R.layout.fragment_choxacnhan

    @Inject
    lateinit var presenter: ChoXacNhanMVPPresenter
    override fun setUp() {
        presenter.onAttach(this)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}