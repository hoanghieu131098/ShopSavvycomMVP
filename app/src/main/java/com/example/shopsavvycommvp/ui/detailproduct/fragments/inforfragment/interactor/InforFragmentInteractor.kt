package com.example.shopsavvycommvp.ui.detailproduct.fragments.inforfragment.interactor

import com.example.shopsavvycommvp.data.network.AppApi
import com.example.shopsavvycommvp.ui.base.interactor.BaseInteractor
import javax.inject.Inject

class InforFragmentInteractor @Inject constructor(apiHelper: AppApi): BaseInteractor(apiHelper),InforFragmentMVPInteractor {
}