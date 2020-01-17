package com.example.shopsavvycommvp.ui.notiorder.fragments.dagiao.view

import com.example.shopsavvycommvp.R
import com.example.shopsavvycommvp.ui.base.view.BaseFragment
import com.example.shopsavvycommvp.ui.notiorder.fragments.dagiao.presenter.DaGiaoMVPPresenter
import com.example.shopsavvycommvp.ui.notiorder.fragments.danggiao.presenter.DangGiaoMVPPresenter
import javax.inject.Inject

class DaGiaoFragment : BaseFragment(), DaGiaoMVPView {
    override val layoutId: Int
        get() = R.layout.fragment_dagiao
    @Inject
    lateinit var presenter: DaGiaoMVPPresenter

    override fun setUp() {
        presenter.onAttach(this)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}