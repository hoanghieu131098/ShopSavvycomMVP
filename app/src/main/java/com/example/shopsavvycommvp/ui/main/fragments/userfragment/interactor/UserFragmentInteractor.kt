package com.example.shopsavvycommvp.ui.main.fragments.userfragment.interactor

import com.example.shopsavvycommvp.data.network.AppApi
import com.example.shopsavvycommvp.ui.base.interactor.BaseInteractor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class UserFragmentInteractor @Inject constructor(apiHelper: AppApi): BaseInteractor(apiHelper),UserFragmentMVPInteractor {
    override fun getFirebase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    override fun getAccount(): FirebaseAuth {
       return FirebaseAuth.getInstance()
    }
}