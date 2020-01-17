package com.example.shopsavvycommvp.ui.order.interactor

import com.example.shopsavvycommvp.ui.base.interactor.MVPInteractor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

interface OrderMVPInteractor : MVPInteractor {
    fun getFirebase(): FirebaseDatabase
    fun getAccount(): FirebaseAuth
}