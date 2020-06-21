package com.example.shopsavvycommvp.ui.admindetail.interactor

import com.example.shopsavvycommvp.ui.base.interactor.MVPInteractor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

interface AdminDetailMVPInteractor  : MVPInteractor {
    fun getFirebase(): FirebaseDatabase
    fun getAccount(): FirebaseAuth
    fun getFirebaseStorage(): FirebaseStorage
}