package com.example.shopsavvycommvp.ui.base.view

interface MVPView {
    fun showProgress()

    fun hideProgress()

    fun handleThrowableError(throwable: Throwable)

    fun showError(msg: String)
}