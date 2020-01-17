package com.example.shopsavvycommvp.ui.base.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.example.shopsavvycommvp.R
import com.example.shopsavvycommvp.data.network.ErrorHandler
import com.example.shopsavvycommvp.widget.NavData
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment : Fragment(), MVPView {
    var baseActivity: BaseActivity? = null
        private set
    /**
     * @return layout resource id
     */
    @get:LayoutRes
    protected abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onAttach(context: Context) {
        performDependencyInjection()
        super.onAttach(context)
        if (context is BaseActivity) {
            baseActivity = context
        }
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
    protected val navController: NavController?
        get() = baseActivity!!.navData?.value?.navController
    var isOnBackHandle: Boolean = false
    private var mRootView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (this.mRootView == null) {
            this.mRootView = inflater.inflate(layoutId, container, false)
        }
        if (this.mRootView!!.parent != null) {
            (this.mRootView!!.parent as ViewGroup).removeView(this.mRootView)
        }

        return mRootView
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun performDependencyInjection() {
        AndroidSupportInjection.inject(this)
    }

    open fun onHandleBackIfNeeded() {}

    override fun showProgress() {
        baseActivity!!.showProgress()
    }

    override fun hideProgress() {
        baseActivity!!.hideProgress()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    override fun handleThrowableError(throwable: Throwable) {
//        Log.e("----", "-------2" + throwable.message + this.javaClass.name)
//        Log.e("----", "-------2" + throwable.message + this.javaClass.canonicalName)
//        Log.e("----", "-------2" + throwable.message + this.javaClass.simpleName)
        if (!ErrorHandler.handle(
                context!!,
                throwable
            ).equals(context!!.getString(R.string.error_occurred))
        ) {
            baseActivity?.showAlert(ErrorHandler.handle(context!!, throwable))
        }
        hideProgress()
        baseActivity?.showAlert(ErrorHandler.handle(context!!, throwable))
    }

    protected abstract fun setUp()
}