package com.example.shopsavvycommvp.ui.notiorder.fragments.cholayhang.view

import com.example.shopsavvycommvp.R
import com.example.shopsavvycommvp.ui.base.view.BaseFragment
import com.example.shopsavvycommvp.ui.notiorder.fragments.cholayhang.presenter.ChoLayHangMVPPresenter
import javax.inject.Inject

class ChoLayHangFragment: BaseFragment(),ChoLayHangMVPView {
    override val layoutId: Int
        get() = R.layout.fragment_cholayhang

    @Inject
    lateinit var presenter: ChoLayHangMVPPresenter
    override fun setUp() {
        presenter.onAttach(this)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}