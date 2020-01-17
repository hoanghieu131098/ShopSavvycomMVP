package com.example.shopsavvycommvp.ui.notiorder.fragments.trahang.interactor

import com.example.shopsavvycommvp.data.network.AppApi
import com.example.shopsavvycommvp.ui.base.interactor.BaseInteractor
import javax.inject.Inject

class TraHangInteractor @Inject constructor(api: AppApi): BaseInteractor(api),TraHangMVPInteractor{
}