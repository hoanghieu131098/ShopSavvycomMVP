package com.example.shopsavvycommvp.ui.notiorder.fragments.trahang.view

import com.example.shopsavvycommvp.R
import com.example.shopsavvycommvp.ui.base.view.BaseFragment
import com.example.shopsavvycommvp.ui.notiorder.fragments.trahang.presenter.TraHangMVPPresenter
import javax.inject.Inject

class TraHangFragment : BaseFragment(), TraHangMVPView {
    override val layoutId: Int
        get() = R.layout.fragment_trahang
    @Inject
    lateinit var presenter: TraHangMVPPresenter

    override fun setUp() {
        presenter.onAttach(this)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}