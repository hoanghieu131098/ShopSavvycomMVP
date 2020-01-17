package com.example.shopsavvycommvp.ui.notiorder.fragments.danggiao.view

import com.example.shopsavvycommvp.data.network.request.Order
import com.example.shopsavvycommvp.ui.base.view.MVPView

interface DangGiaoMVPView : MVPView{
    fun onReponseFailed(msg: String)
    fun onReponseSuccess(data: ArrayList<Order>)

    fun onReponseUpdateActive(msg: String)
}