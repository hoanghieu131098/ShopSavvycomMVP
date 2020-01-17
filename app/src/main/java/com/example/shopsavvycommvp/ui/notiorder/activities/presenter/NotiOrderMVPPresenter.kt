package com.example.shopsavvycommvp.ui.notiorder.activities.presenter

import com.example.shopsavvycommvp.ui.base.presenter.MVPPresenter
import com.example.shopsavvycommvp.ui.notiorder.activities.interactor.NotiOrderMVPInteractor
import com.example.shopsavvycommvp.ui.notiorder.activities.view.NotiOrderMVPView

interface NotiOrderMVPPresenter<V: NotiOrderMVPView,I: NotiOrderMVPInteractor> : MVPPresenter<V,I> {
}