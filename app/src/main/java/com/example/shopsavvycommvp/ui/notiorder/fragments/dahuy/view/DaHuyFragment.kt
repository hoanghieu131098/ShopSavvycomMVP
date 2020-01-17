package com.example.shopsavvycommvp.ui.notiorder.fragments.dahuy.view

import com.example.shopsavvycommvp.R
import com.example.shopsavvycommvp.ui.base.view.BaseFragment
import com.example.shopsavvycommvp.ui.notiorder.fragments.dahuy.presenter.DaHuyMVPPresenter
import javax.inject.Inject

class DaHuyFragment : BaseFragment(), DaHuyMVPView {
    override val layoutId: Int
        get() = R.layout.fragment_dahuy
    @Inject
    lateinit var presenter: DaHuyMVPPresenter

    override fun setUp() {
        presenter.onAttach(this)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}