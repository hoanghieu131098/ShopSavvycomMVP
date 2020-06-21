package com.example.shopsavvycommvp.ui.admindetail.interactor

import com.example.shopsavvycommvp.data.network.AppApi
import com.example.shopsavvycommvp.ui.admin.interactor.AdminActivityMVPInteractor
import com.example.shopsavvycommvp.ui.base.interactor.BaseInteractor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject

class AdminDetailInteractor  @Inject constructor(apiHelper: AppApi): BaseInteractor(apiHelper),
    AdminDetailMVPInteractor {
    override fun getFirebase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    override fun getAccount(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
    override fun getFirebaseStorage(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }
}