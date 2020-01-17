package com.example.shopsavvycommvp.ui.notiorder.fragments.choxacnhan.interactor

import com.example.shopsavvycommvp.data.network.AppApi
import com.example.shopsavvycommvp.ui.base.interactor.BaseInteractor
import javax.inject.Inject

class ChoXacNhanInteractor @Inject constructor(api: AppApi): BaseInteractor(api),ChoXacNhanMVPInteractor {
}