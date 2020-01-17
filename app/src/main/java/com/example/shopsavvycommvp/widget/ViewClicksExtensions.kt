package com.example.shopsavvycommvp.widget

import android.view.View
import com.example.shopsavvycommvp.MvpApp

import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

fun View.click(disable: Boolean = true): Observable<kotlin.Unit> {
    return clicks().doOnError { }.throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).filter { (context?.applicationContext as MvpApp).isValidContinueClick(this, disable) }
}
