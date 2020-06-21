package com.example.shopsavvycommvp.ui.admin.interactor

import com.example.shopsavvycommvp.ui.base.interactor.MVPInteractor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

interface AdminActivityMVPInteractor : MVPInteractor {
    fun getFirebase(): FirebaseDatabase
    fun getAccount(): FirebaseAuth
}