package com.example.shopsavvycommvp.ui.detailproduct.fragments.ratingfragment.interactor

import com.example.shopsavvycommvp.data.network.response.Comment
import com.example.shopsavvycommvp.ui.base.interactor.MVPInteractor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Observable

interface RatingFragmentMVPInteractor : MVPInteractor {
    fun getFirebase(): FirebaseDatabase
    fun getAccount(): FirebaseAuth
}