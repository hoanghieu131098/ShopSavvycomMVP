package com.example.shopsavvycommvp.ui.main.fragments.cartfragment.interactor

import com.example.shopsavvycommvp.data.network.request.Cart
import com.example.shopsavvycommvp.ui.base.interactor.MVPInteractor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

interface CartFragmentMVPInteractor: MVPInteractor {
    fun getFirebase(): FirebaseDatabase
    fun getAccount(): FirebaseAuth

}