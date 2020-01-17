package com.example.shopsavvycommvp.util

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.text.Html
import android.text.TextUtils
import com.example.shopsavvycommvp.R

object DialogUtils {
    val FLAG_RESOURCE_NULL: String? = null

    fun getAlertDialog(context: Context, title: String, message: String, positiveButton: String?, negativeButton: String?, neutralButton: String?, callBack: DialogCallBack?): AlertDialog {
        val alertDialogBuilder = AlertDialog.Builder(context)


        if (!TextUtils.isEmpty(positiveButton)) {
            alertDialogBuilder.setPositiveButton(positiveButton) { dialogInterface, i ->
                dialogInterface.dismiss()
                callBack?.onClickDialog(dialogInterface, DialogInterface.BUTTON_POSITIVE)
            }
        }

        if (!TextUtils.isEmpty(negativeButton)) {
            alertDialogBuilder.setNegativeButton(negativeButton) { dialogInterface, i ->
                dialogInterface.dismiss()
                callBack?.onClickDialog(dialogInterface, DialogInterface.BUTTON_NEGATIVE)
            }
        }

        if (!TextUtils.isEmpty(neutralButton)) {
            alertDialogBuilder.setNeutralButton(neutralButton) { dialogInterface, i ->
                dialogInterface.dismiss()
                callBack?.onClickDialog(dialogInterface, DialogInterface.BUTTON_NEUTRAL)
            }
        }
        alertDialogBuilder.setTitle(title)
        alertDialogBuilder.setMessage(message)
        val alertDialog = alertDialogBuilder.create()
        alertDialog.setCanceledOnTouchOutside(true)
        alertDialog.setCancelable(true)

        return alertDialog
    }

    /**
     * Get AlertDialog with 2 buttons button no title
     *
     * @param context
     * @param messageResId
     * @param positiveButtonResId
     * @param negativeButtonResId
     * @param callBack
     * @return
     */
    fun getAlertDialog(context: Context, messageResId: Int, positiveButtonResId: Int, negativeButtonResId: Int, callBack: DialogCallBack): AlertDialog {
        return getAlertDialog(context, context.getString(R.string.app_name), context.getString(messageResId), context.getString(positiveButtonResId), context.getString(negativeButtonResId), FLAG_RESOURCE_NULL, callBack)
    }

    fun getAlertDialog(context: Context, messageContent: String, positiveButtonResId: Int, negativeButtonResId: Int, callBack: DialogCallBack): AlertDialog {
        return getAlertDialog(context, context.getString(R.string.app_name), messageContent, context.getString(positiveButtonResId), context.getString(negativeButtonResId), FLAG_RESOURCE_NULL, callBack)
    }

    /**
     * Get AlertDialog with one negative button
     *
     * @param context
     * @param messageResId
     * @param negativeButtonResId
     * @param callBack
     * @return
     */
    fun getAlertDialog(context: Context, messageResId: Int, negativeButtonResId: Int, callBack: DialogCallBack): AlertDialog {
        return getAlertDialog(context, context.getString(R.string.app_name), context.getString(messageResId), FLAG_RESOURCE_NULL, context.getString(negativeButtonResId), FLAG_RESOURCE_NULL, callBack)
    }

    /**
     * Get AlertDialog with one negative button
     *
     * @param context
     * @param messageContent
     * @param negativeButtonResId
     * @param callBack
     * @return
     */
    fun getAlertDialog(context: Context, messageContent: String, negativeButtonResId: Int, callBack: DialogCallBack): AlertDialog {
        return getAlertDialog(context, context.getString(R.string.app_name), messageContent, FLAG_RESOURCE_NULL, context.getString(negativeButtonResId), FLAG_RESOURCE_NULL, callBack)
    }




    fun getOkCancelAlertDialog(pContext: Context, pMsgContentResId: Int, pDialogCallBack: DialogCallBack): AlertDialog {
        return getAlertDialog(pContext, pContext.getString(R.string.app_name), pContext.getString(pMsgContentResId), pContext.getString(R.string.ok), pContext.getString(R.string.cancel), FLAG_RESOURCE_NULL, pDialogCallBack)
    }

    fun getRetryCancelAlertDialog(pContext: Context, pMsgContentResId: Int, pDialogCallBack: DialogCallBack): AlertDialog {
        return getAlertDialog(pContext, pContext.getString(R.string.app_name), pContext.getString(pMsgContentResId), pContext.getString(R.string.retry), pContext.getString(R.string.cancel), FLAG_RESOURCE_NULL, pDialogCallBack)
    }

    fun getOkCancelAlertDialog(pContext: Context, pMsgContentResContent: String, pDialogCallBack: DialogCallBack): AlertDialog {
        return getAlertDialog(pContext, pContext.getString(R.string.app_name), pMsgContentResContent, pContext.getString(R.string.ok), pContext.getString(R.string.cancel), FLAG_RESOURCE_NULL, pDialogCallBack)
    }

    fun getYesNoAlertDialog(pContext: Context, pMsgContentResContent: String, pDialogCallBack: DialogCallBack): AlertDialog {
        return getAlertDialog(pContext, pContext.getString(R.string.app_name), pMsgContentResContent, pContext.getString(R.string.yes), pContext.getString(R.string.no), FLAG_RESOURCE_NULL, pDialogCallBack)
    }

    fun getYesNoAlertDialog(pContext: Context, pMsgContentResId: Int, pDialogCallBack: DialogCallBack): AlertDialog {
        return getAlertDialog(pContext, pContext.getString(R.string.app_name), pContext.getString(pMsgContentResId), pContext.getString(R.string.yes), pContext.getString(R.string.no), FLAG_RESOURCE_NULL, pDialogCallBack)
    }

    fun getYesNoAlertDialog(pContext: Context, titleResId: Int, pMsgContentResId: Int, pDialogCallBack: DialogCallBack): AlertDialog {
        return getAlertDialog(pContext, pContext.getString(titleResId), pContext.getString(pMsgContentResId), pContext.getString(R.string.yes), pContext.getString(R.string.no), FLAG_RESOURCE_NULL, pDialogCallBack)
    }

    fun getOkCancelAlertDialog(pContext: Context, pMsgContentResId: Int): AlertDialog {
        return getAlertDialog(pContext, pContext.getString(R.string.app_name), pContext.getString(pMsgContentResId), pContext.getString(R.string.ok), pContext.getString(R.string.cancel), FLAG_RESOURCE_NULL, SimpleDialogDismissListener())
    }

    fun getAlertDialogNotice(pContext: Context, pTitle: String, pMsgContentRes: String, positiveString: String): AlertDialog {
        return getAlertDialog(pContext, pTitle, pMsgContentRes, positiveString, FLAG_RESOURCE_NULL, FLAG_RESOURCE_NULL, SimpleDialogDismissListener())
    }

    /**
     * Van.NT Show alert dialog with ok button
     *
     * @param pContext
     * @param pMsgContentResId
     * @param pDialogCallBack
     * @return
     */
    fun getOkAlertDialog(pContext: Context, titleResId: Int, pMsgContentResId: Int, pDialogCallBack: DialogCallBack): AlertDialog {
        return getAlertDialog(pContext, pContext.getString(titleResId), pContext.getString(pMsgContentResId), pContext.getString(R.string.ok), FLAG_RESOURCE_NULL, FLAG_RESOURCE_NULL, pDialogCallBack)
    }

    fun getOkAlertDialog(pContext: Context, pMsgContentResId: Int, pDialogCallBack: DialogCallBack): AlertDialog {
        return getOkAlertDialog(pContext, R.string.app_name, pMsgContentResId, pDialogCallBack)
    }

    fun getOkAlertDialog(pContext: Context, pMsgContent: String, pDialogCallBack: DialogCallBack): AlertDialog {
        return getAlertDialog(pContext, pContext.getString(R.string.app_name), pMsgContent, pContext.getString(R.string.ok), FLAG_RESOURCE_NULL, FLAG_RESOURCE_NULL, pDialogCallBack)
    }

    fun getOkAlertDialog(pContext: Context, titleResId: Int, pMsgContentResId: Int): AlertDialog {
        return getAlertDialog(pContext, pContext.getString(titleResId), pContext.getString(pMsgContentResId), pContext.getString(R.string.ok), FLAG_RESOURCE_NULL, FLAG_RESOURCE_NULL, SimpleDialogDismissListener())
    }

    fun getOkAlertDialog(pContext: Context, pMsgContentResId: Int): AlertDialog {
        return getAlertDialog(pContext, pContext.getString(R.string.app_name), pContext.getString(pMsgContentResId), pContext.getString(R.string.ok), FLAG_RESOURCE_NULL, FLAG_RESOURCE_NULL, SimpleDialogDismissListener())
    }

    fun getOkAlertDialog(pContext: Context, pMsgContentRes: String): AlertDialog {
        return getAlertDialog(pContext, pContext.getString(R.string.app_name), pMsgContentRes, pContext.getString(R.string.ok), FLAG_RESOURCE_NULL, FLAG_RESOURCE_NULL, SimpleDialogDismissListener())
    }


    fun getAlertDialogNotFound(context: Context, message: String, documentName: String): AlertDialog {
        return getOkAlertDialog(context, Html.fromHtml(String.format(message, documentName)).toString())
    }


    fun getDismissAlertDialog(pContext: Context, pMsgContentId: Int, pDialogCallBack: DialogCallBack?): AlertDialog {
        return getAlertDialog(pContext, pContext.getString(R.string.app_name), pContext.getString(pMsgContentId), pContext.getString(R.string.ok), FLAG_RESOURCE_NULL, FLAG_RESOURCE_NULL, pDialogCallBack)
    }

    fun getDismissAlertDialog(pContext: Context, mess: String, pDialogCallBack: DialogCallBack?): AlertDialog {
        return getAlertDialog(pContext, pContext.getString(R.string.app_name), mess, pContext.getString(R.string.ok), FLAG_RESOURCE_NULL, FLAG_RESOURCE_NULL, pDialogCallBack)
    }

    fun getErrorMessageAlertDialog(pContext: Context, mess: String): AlertDialog {
        return getAlertDialog(pContext, pContext.getString(R.string.label_error), mess, pContext.getString(R.string.ok), FLAG_RESOURCE_NULL, FLAG_RESOURCE_NULL, SimpleDialogDismissListener())
    }


    interface DialogCallBack {
        /**
         * @param pAlertDialog
         * @param pDialogType
         */
        fun onClickDialog(pAlertDialog: DialogInterface, pDialogType: Int)
    }

    class SimpleDialogDismissListener : DialogCallBack {

        /**
         * @param pDialogType
         */
        override fun onClickDialog(pAlertDialog: DialogInterface, pDialogType: Int) {
            pAlertDialog.dismiss()
        }
    }
}