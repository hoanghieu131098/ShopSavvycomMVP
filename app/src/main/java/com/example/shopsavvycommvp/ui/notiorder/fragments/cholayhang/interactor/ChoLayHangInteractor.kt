package com.example.shopsavvycommvp.ui.notiorder.fragments.cholayhang.interactor

import com.example.shopsavvycommvp.data.network.AppApi
import com.example.shopsavvycommvp.ui.base.interactor.BaseInteractor
import javax.inject.Inject

class ChoLayHangInteractor @Inject  constructor(api: AppApi): BaseInteractor(api),ChoLayHangMVPInteractor{
}