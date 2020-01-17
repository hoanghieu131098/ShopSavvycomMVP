package com.example.shopsavvycommvp.ui.detailproduct.activities.view

import com.example.shopsavvycommvp.ui.base.view.MVPView

interface DetailProductMVPView: MVPView {
  fun onResponse(msg: String)
}