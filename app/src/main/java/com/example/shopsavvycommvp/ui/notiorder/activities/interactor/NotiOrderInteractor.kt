package com.example.shopsavvycommvp.ui.notiorder.activities.interactor

import com.example.shopsavvycommvp.data.network.AppApi
import com.example.shopsavvycommvp.ui.base.interactor.BaseInteractor
import javax.inject.Inject

class NotiOrderInteractor @Inject constructor(apiHelper: AppApi): BaseInteractor(apiHelper),NotiOrderMVPInteractor{
}