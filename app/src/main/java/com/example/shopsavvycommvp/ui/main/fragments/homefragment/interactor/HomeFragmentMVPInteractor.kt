package com.example.shopsavvycommvp.ui.main.fragments.homefragment.interactor


import com.example.shopsavvycommvp.data.network.response.Category
import com.example.shopsavvycommvp.data.network.response.Product
import com.example.shopsavvycommvp.data.network.response.Response
import com.example.shopsavvycommvp.ui.base.interactor.MVPInteractor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Observable

interface HomeFragmentMVPInteractor : MVPInteractor{
    fun getFirebase(): FirebaseDatabase
    fun getAccount(): FirebaseAuth
}