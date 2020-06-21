package com.example.shopsavvycommvp.ui.detailcategory.interactor

import com.example.shopsavvycommvp.data.network.response.Product
import com.example.shopsavvycommvp.ui.base.interactor.MVPInteractor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Observable


interface DetailCategoryMVPInteractor: MVPInteractor {
    fun getFirebase(): FirebaseDatabase
    fun getAccount(): FirebaseAuth
}