package com.example.shopsavvycommvp.ui.main.fragments.fashionfragment.interactor

import com.example.shopsavvycommvp.data.network.AppApi
import com.example.shopsavvycommvp.ui.base.interactor.BaseInteractor
import javax.inject.Inject

class FashionFragmentInteractor @Inject constructor(apiHelper: AppApi): BaseInteractor(apiHelper),FashionFragmentMVPInteractor {
}