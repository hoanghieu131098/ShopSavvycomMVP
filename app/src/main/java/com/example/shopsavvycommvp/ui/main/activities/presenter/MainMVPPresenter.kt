package com.example.shopsavvycommvp.ui.main.activities.presenter

import com.example.shopsavvycommvp.ui.base.presenter.MVPPresenter
import com.example.shopsavvycommvp.ui.main.activities.interactor.MainMVPInteractor
import com.example.shopsavvycommvp.ui.main.activities.view.MainMVPView

interface MainMVPPresenter<V : MainMVPView, I : MainMVPInteractor> : MVPPresenter<V, I> {
}