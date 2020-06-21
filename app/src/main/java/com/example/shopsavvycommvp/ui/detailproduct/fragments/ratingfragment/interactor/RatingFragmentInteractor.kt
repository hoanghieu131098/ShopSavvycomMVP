package com.example.shopsavvycommvp.ui.detailproduct.fragments.ratingfragment.interactor

import com.example.shopsavvycommvp.data.network.AppApi
import com.example.shopsavvycommvp.data.network.response.Comment
import com.example.shopsavvycommvp.ui.base.interactor.BaseInteractor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Observable
import javax.inject.Inject

class RatingFragmentInteractor @Inject constructor(apiHelper: AppApi) : BaseInteractor(apiHelper),
    RatingFragmentMVPInteractor {

    override fun getFirebase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    override fun getAccount(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}