package com.example.shopsavvycommvp.ui.main.activities.interactor

import com.example.shopsavvycommvp.data.network.AppApi
import com.example.shopsavvycommvp.data.preferences.MvpPreferences
import com.example.shopsavvycommvp.ui.base.interactor.BaseInteractor
import javax.inject.Inject

class MainInteractor @Inject internal constructor(preferenceHelper: MvpPreferences, apiHelper: AppApi) : BaseInteractor(preferenceHelper, apiHelper), MainMVPInteractor  {
}