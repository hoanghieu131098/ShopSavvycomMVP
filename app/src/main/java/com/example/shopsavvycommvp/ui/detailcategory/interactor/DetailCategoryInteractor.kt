package com.example.shopsavvycommvp.ui.detailcategory.interactor

import com.example.shopsavvycommvp.data.network.AppApi
import com.example.shopsavvycommvp.data.network.response.Product
import com.example.shopsavvycommvp.ui.base.interactor.BaseInteractor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Observable
import javax.inject.Inject


class DetailCategoryInteractor @Inject constructor(apiHelper: AppApi): BaseInteractor(apiHelper),DetailCategoryMVPInteractor {
    override fun getFirebase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    override fun getAccount(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}