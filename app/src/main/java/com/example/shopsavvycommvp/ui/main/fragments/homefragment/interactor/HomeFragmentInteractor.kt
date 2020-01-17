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
    override fun searchProduct(q: String?): Observable<List<Product>> {
        return appApi.searchProduct(q)
    }

    override fun getFirebase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    override fun getAccount(): FirebaseAuth {
       return FirebaseAuth.getInstance()
    }

    override fun getproductAll(key: String): Observable<List<Product>> {
        return appApi.getProduct(key)
    }

    override fun getcategoryAll(): Observable<List<Category>> {
        Log.d("testCate", "interactor")
        return appApi.getCategory()
    }

}