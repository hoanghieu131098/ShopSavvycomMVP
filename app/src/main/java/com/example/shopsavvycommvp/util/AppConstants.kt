package com.example.shopsavvycommvp.util

interface AppConstants {
    companion object {
        val API_ERROR_STATUS = "error"
        val INTENT_ITEM_TO_DETAIL = "productHome"
        val INTENT_ITEM_TO_DETAIL_CATEGORY = "categoryHome"
        val SET_Argument_Infor = "description"
        val SET_Argument_Rating = "idProduct"
        val INTENT_DATA_TO_ORDER = "dataCart"
        val INTENT_TOTAL_DATA_TO_ORDER = "dataTOTAL"
        val LINK_STORAGE_FIREBASE = "gs://shopsavvycom.appspot.com"
        const val TYPE_PRODUCT_ADMIN = "product_admin"
        const val TYPE_PRODUCT_HOME = "product_home"
        const val EMAIL_ADMIN = "Ngothetu98@gmail.com"
    }

    interface AdminDetail {
        companion object {
            val IS_EDIT_PRODUCT = "editProduct"
            val PRODUCT = "Product"
            val LIST_PRODUCT_SIZE = "Size"
        }
    }

    interface DangGiao {
        companion object {
            val INTENT_ID_ORDER = "idOrder"
        }
    }

    interface TAG {
        companion object {
            val CART = "CART"
            val DETAILORDER = "DETAILORDER"
        }
    }

    interface Rate {
        companion object {
            val UPLOAD_REPLY_FAILED = "upload Reply Failed"
        }
    }

    interface Category {
        companion object {
            const val CATEGORY = "Category"
        }
    }

    interface Product {
        companion object {
            const val PRODUCT = "Product"
            const val COMMENTS = "comments"
            const val REPLY = "reply"
            const val IMAGES = "Image"
        }
    }
}