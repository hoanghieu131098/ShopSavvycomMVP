package com.example.shopsavvycommvp.data

import com.example.shopsavvycommvp.data.network.response.Account

object AccountManager {
    var account: Account? = null

    fun clearUserLogined() {
        account = null
    }
}