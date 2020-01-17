package com.example.shopsavvycommvp.ui.main.fragments.cartfragment.interactor

import com.example.shopsavvycommvp.data.network.AppApi
import com.example.shopsavvycommvp.data.network.request.Cart
import com.example.shopsavvycommvp.ui.base.interactor.BaseInteractor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class CartFragmentInteractor @Inject constructor(apiHelper: AppApi): BaseInteractor(apiHelper),CartFragmentMVPInteractor {
    override fun getAccount(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    override fun getFirebase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

}