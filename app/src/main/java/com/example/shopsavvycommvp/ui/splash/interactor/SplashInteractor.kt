package com.example.shopsavvycommvp.ui.splash.interactor

import com.example.shopsavvycommvp.data.network.AppApi
import com.example.shopsavvycommvp.data.preferences.MvpPreferences
import com.example.shopsavvycommvp.ui.base.interactor.BaseInteractor
import javax.inject.Inject

class SplashInteractor @Inject constructor(preferences: MvpPreferences,apiHelper: AppApi): BaseInteractor(preferences,apiHelper),SplashMVPInteractor {
}