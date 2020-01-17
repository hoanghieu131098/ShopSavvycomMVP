package com.example.shopsavvycommvp.ui.notiorder.fragments.dagiao.interactor

import com.example.shopsavvycommvp.data.network.AppApi
import com.example.shopsavvycommvp.ui.base.interactor.BaseInteractor
import javax.inject.Inject

class DaGiaoInteractor @Inject constructor(api: AppApi) : BaseInteractor(api),DaGiaoMVPInteractor {
}