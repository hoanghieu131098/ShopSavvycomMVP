package com.example.shopsavvycommvp.ui.login.interactor

import com.example.shopsavvycommvp.data.network.AppApi
import com.example.shopsavvycommvp.ui.base.interactor.BaseInteractor
import javax.inject.Inject

class LoginInteractor @Inject constructor(apiHelper: AppApi):BaseInteractor(apiHelper),LoginMVPInteractor {
}