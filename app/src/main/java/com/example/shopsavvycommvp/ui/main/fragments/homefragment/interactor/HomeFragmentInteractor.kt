package com.example.shopsavvycommvp.ui.main.fragments.homefragment.interactor

import android.util.Log
import com.example.shopsavvycommvp.data.network.AppApi

import com.example.shopsavvycommvp.data.network.response.Category
import com.example.shopsavvycommvp.data.network.response.Product
import com.example.shopsavvycommvp.data.network.response.Response
import com.example.shopsavvycommvp.ui.base.interactor.BaseInteractor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Observable
import javax.inject.Inject


class HomeFragmentInteractor @Inject constructor(apiHelper: AppApi) : BaseInteractor(apiHelper),HomeFragmentMVPInteractor {
    override fun getFirebase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    override fun getAccount(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}