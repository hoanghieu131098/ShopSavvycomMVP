package com.example.shopsavvycommvp.ui.main.fragments.userfragment.presenter

import com.example.shopsavvycommvp.ui.base.presenter.MVPPresenter
import com.example.shopsavvycommvp.ui.main.fragments.userfragment.interactor.UserFragmentMVPInteractor
import com.example.shopsavvycommvp.ui.main.fragments.userfragment.view.UserFragmentMVPView

interface UserFragmentMVPPresenter: MVPPresenter<UserFragmentMVPView,UserFragmentMVPInteractor> {
}