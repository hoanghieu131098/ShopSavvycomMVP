package com.example.shopsavvycommvp.ui.base.view

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LiveData
import cn.jzvd.Jzvd
import com.example.shopsavvycommvp.R
import com.example.shopsavvycommvp.data.network.ErrorHandler
import com.example.shopsavvycommvp.util.BackUtils
import com.example.shopsavvycommvp.util.DialogUtils
import com.example.shopsavvycommvp.util.ProgressDialogUtils
import com.example.shopsavvycommvp.widget.NavData
import dagger.android.AndroidInjection
import dagger.android.HasActivityInjector
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

abstract class BaseActivity : AppCompatActivity(), MVPView {
    private var progressDialog: ProgressDialogUtils? = null
    @get:LayoutRes
    protected abstract val layoutId: Int

    protected abstract fun setUp()

    private var fragactive: Fragment? = null
    var navData: LiveData<NavData>? = null
    override fun onDestroy() {
        super.onDestroy()
        progressDialog = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        performDI()
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        setUp()
    }

    private fun performDI() {
        AndroidInjection.inject(this)
    }

    override fun showProgress() {
        if (progressDialog == null) {
            progressDialog = ProgressDialogUtils.instance
        }
        progressDialog!!.show(this)
    }

    override fun hideProgress() {
        if (progressDialog != null) {
            progressDialog!!.dismiss()
            progressDialog = null
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navData?.value?.navController?.navigateUp() ?: false
    }

    protected open fun currentFragment(): BaseFragment? {
        return navData?.value?.navHostFragment?.childFragmentManager?.fragments?.get(0) as BaseFragment?
    }

    override fun onBackPressed() {
        if (navData != null) {
            // get last fragment for your handle OnBackPressed()
            val baseFragment =
                navData?.value?.navHostFragment?.childFragmentManager!!.fragments[0] as BaseFragment
            if (baseFragment.isOnBackHandle) {
                baseFragment.onHandleBackIfNeeded()
            } else if (Jzvd.backPress()) {
                return
            } else {
                // handle onBackPress NavigationUI( must be Override if needed)
                if (navData?.value?.navController?.navigateUp() != true) {
//                    super.onBackPressed()
                    BackUtils.onClickExit(
                        this,
                        getString(com.example.shopsavvycommvp.R.string.double_tap_to_exit)
                    )
                }
            }
        } else if (Jzvd.backPress()) {
            return
        } else {
            BackUtils.onClickExit(this, getString(R.string.double_tap_to_exit))
        }
    }

    override fun handleThrowableError(throwable: Throwable) {
        hideProgress()
        showAlert(ErrorHandler.handle(this, throwable))
    }

    /**
     * Show an Alert
     *
     * @param message
     */
    fun showAlert(
        message: String,
        positiveButton: String,
        negativeButton: String?,
        neutralButton: String?,
        pDialogCallBack: DialogUtils.DialogCallBack?
    ): AlertDialog? {
        return showAlert(
            message,
            positiveButton,
            negativeButton,
            neutralButton,
            true,
            pDialogCallBack
        )
    }

    private var mDialogShowCount: Int = 0

    /**
     * Show an Alert
     *
     * @param message
     */
    fun showAlert(
        message: String,
        positiveButton: String,
        negativeButton: String?,
        neutralButton: String?,
        isCancel: Boolean,
        pDialogCallBack: DialogUtils.DialogCallBack?
    ): AlertDialog? {
        if (!isFinishing && mDialogShowCount == 0) {
            try {
                val alertDialog = DialogUtils.getAlertDialog(
                    this@BaseActivity,
                    getString(com.example.shopsavvycommvp.R.string.app_name),
                    message,
                    positiveButton,
                    negativeButton,
                    neutralButton,
                    object : DialogUtils.DialogCallBack {
                        override fun onClickDialog(
                            pAlertDialog: DialogInterface,
                            pDialogType: Int
                        ) {
                            pAlertDialog.dismiss()
                            pDialogCallBack?.onClickDialog(pAlertDialog, pDialogType)
                        }

                    })
                alertDialog.setOnDismissListener { mDialogShowCount-- }
                alertDialog.setCancelable(isCancel)
                alertDialog.setCanceledOnTouchOutside(isCancel)
                alertDialog.show()
                mDialogShowCount++
                return alertDialog
            } catch (iThrowable: Throwable) {
                iThrowable.printStackTrace()
            }

        }
        return null
    }

    fun showAlert(
        message: String,
        positiveButton: String,
        negativeButton: String?,
        pDialogCallBack: DialogUtils.DialogCallBack?
    ): AlertDialog? {
        return showAlert(message, positiveButton, negativeButton, null, pDialogCallBack)
    }

    fun showAlert(
        message: String,
        positiveButton: String,
        negativeButton: String?,
        isCancel: Boolean,
        pDialogCallBack: DialogUtils.DialogCallBack
    ): AlertDialog? {
        return showAlert(message, positiveButton, negativeButton, null, isCancel, pDialogCallBack)
    }

    fun showAlert(
        message: String,
        positiveButton: String,
        pDialogCallBack: DialogUtils.DialogCallBack?
    ): AlertDialog? {
        return showAlert(message, positiveButton, null, pDialogCallBack)
    }

    fun showAlert(
        message: String,
        positiveButton: String,
        isCancel: Boolean,
        pDialogCallBack: DialogUtils.DialogCallBack
    ): AlertDialog? {
        return showAlert(message, positiveButton, null, isCancel, pDialogCallBack)
    }

    /**
     * Show an Alert
     *
     * @param message
     */
    fun showAlert(message: String): AlertDialog? {
        return showAlert(message, getString(com.example.shopsavvycommvp.R.string.ok), null)
    }

    fun showAlert(@StringRes messageResId: Int): AlertDialog {
        return showAlert(getString(messageResId).toInt())
    }


    /**
     * Show an Alert
     *
     * @param message
     */
    fun showAlert(message: String, callBack: DialogUtils.DialogCallBack): AlertDialog? {
        return showAlert(message, getString(R.string.ok), callBack)
    }

    protected override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }

    fun addFragment(list: MutableList<Fragment>, frLayout: Int) {
        for (i in 0 until list.size) {
            supportFragmentManager.inTransaction {
                add(frLayout, list[i]).hide(list[i])
                if (i == list.size - 1) {
                    fragactive = list[0]
                    show(fragactive!!)
                }
            }
        }
    }

    fun showFragment(fragShow: Fragment) {
        if (fragactive == fragShow)
            return
        supportFragmentManager.inTransaction {
            //            setCustomAnimations(R.anim.anim_fade_in,R.anim.anim_fade_out)
            hide(fragactive!!)
            show(fragShow)
        }
        fragactive = fragShow
    }

    fun toat(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}