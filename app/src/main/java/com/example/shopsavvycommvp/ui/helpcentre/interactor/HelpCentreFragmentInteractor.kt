package com.example.shopsavvycommvp.ui.helpcentre.interactor

import com.example.shopsavvycommvp.data.network.AppApi
import com.example.shopsavvycommvp.ui.base.interactor.BaseInteractor
import com.example.shopsavvycommvp.ui.base.view.BaseActivity
import javax.inject.Inject

class HelpCentreFragmentInteractor @Inject constructor(apiHelper: AppApi) :
    BaseInteractor(apiHelper), HelpCentreFragmentMVPInteractor {
}