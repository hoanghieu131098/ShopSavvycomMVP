package com.example.shopsavvycommvp.ui.notiorder.fragments.dahuy.interactor

import com.example.shopsavvycommvp.data.network.AppApi
import com.example.shopsavvycommvp.ui.base.interactor.BaseInteractor
import javax.inject.Inject

class DaHuyInteractor @Inject constructor(api: AppApi): BaseInteractor(api),DaHuyMVPInteractor {
}