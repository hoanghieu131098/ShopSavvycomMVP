package com.example.shopsavvycommvp.ui.admin.interactor

import com.example.shopsavvycommvp.data.network.AppApi
import com.example.shopsavvycommvp.ui.base.interactor.BaseInteractor
import com.example.shopsavvycommvp.ui.detailcategory.interactor.DetailCategoryMVPInteractor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class AdminActivityInteractor @Inject constructor(apiHelper: AppApi): BaseInteractor(apiHelper),
    AdminActivityMVPInteractor {
    override fun getFirebase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    override fun getAccount(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}