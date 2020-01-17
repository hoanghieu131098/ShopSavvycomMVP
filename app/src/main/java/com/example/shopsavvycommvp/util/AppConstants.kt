package com.example.shopsavvycommvp.util

interface AppConstants {
    companion object{
        val API_ERROR_STATUS = "error"
        val INTENT_ITEM_TO_DETAIL = "productHome"
        val INTENT_ITEM_TO_DETAIL_CATEGORY = "categoryHome"
        val SET_Argument_Infor = "description"
        val SET_Argument_Rating = "idProduct"
        val INTENT_DATA_TO_ORDER = "dataCart"
        val INTENT_TOTAL_DATA_TO_ORDER = "dataTOTAL"
    }
    interface DangGiao{
        companion object{
            val INTENT_ID_ORDER = "idOrder"
        }
    }

    interface TAG{
        companion object{
            val CART = "CART"
            val DETAILORDER = "DETAILORDER"
        }
    }
    interface Rate{
        companion object{
            val UPLOAD_REPLY_FAILED = "upload Reply Failed"
        }
    }
}