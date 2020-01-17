package com.example.shopsavvycommvp.ui.notiorder.fragments.danggiao.interactor

import com.example.shopsavvycommvp.data.network.AppApi
import com.example.shopsavvycommvp.ui.base.interactor.BaseInteractor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class DangGiaoInteractor @Inject constructor(api: AppApi): BaseInteractor(api),DangGiaoMVPInteractor {
    override fun getFirebase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    override fun getAccount(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}