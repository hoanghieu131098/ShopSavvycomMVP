package com.example.shopsavvycommvp.ui.detailproduct.activities.interactor

import com.example.shopsavvycommvp.data.network.response.Account
import com.example.shopsavvycommvp.ui.base.interactor.MVPInteractor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

interface DetailProductMVPInteractor: MVPInteractor {
    fun getFirebase(): FirebaseDatabase
    fun getAccount(): FirebaseAuth

}