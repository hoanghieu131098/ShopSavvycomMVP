package com.example.shopsavvycommvp.ui.detailproduct.activities.interactor

import com.example.shopsavvycommvp.data.network.AppApi
import com.example.shopsavvycommvp.ui.base.interactor.BaseInteractor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class DetailProductInteractor @Inject constructor(apiHelper: AppApi) : BaseInteractor(apiHelper),
    DetailProductMVPInteractor {
    override fun getAccount() = FirebaseAuth.getInstance()

    override fun getFirebase() =  FirebaseDatabase.getInstance()
}