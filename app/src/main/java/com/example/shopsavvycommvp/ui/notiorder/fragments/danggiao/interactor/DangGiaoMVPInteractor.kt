package com.example.shopsavvycommvp.ui.notiorder.fragments.danggiao.interactor

import com.example.shopsavvycommvp.ui.base.interactor.MVPInteractor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

interface DangGiaoMVPInteractor : MVPInteractor{
    fun getFirebase(): FirebaseDatabase
    fun getAccount(): FirebaseAuth


}