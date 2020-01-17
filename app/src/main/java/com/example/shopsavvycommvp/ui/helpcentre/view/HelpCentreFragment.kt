package com.example.shopsavvycommvp.ui.helpcentre.view

import com.example.shopsavvycommvp.R
import com.example.shopsavvycommvp.ui.base.view.BaseFragment
import com.example.shopsavvycommvp.ui.helpcentre.presenter.HelpCentreFragmentMVPPresenter
import com.example.shopsavvycommvp.ui.helpcentre.presenter.HelpCentreFragmentPresenter
import javax.inject.Inject

class HelpCentreFragment : BaseFragment(),HelpCentreFragmentMVPView {
    override val layoutId: Int
        get() = R.layout.fragment_help_centre

    @Inject
    lateinit var presenter: HelpCentreFragmentMVPPresenter
    override fun setUp() {
     presenter.onAttach(this)
        initView()
    }

    private fun initView() {

    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}