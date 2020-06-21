package com.example.shopsavvycommvp.ui.admin.view

import android.annotation.SuppressLint
import android.content.Intent
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.view.menu.MenuPopupHelper
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shopsavvycommvp.R
import com.example.shopsavvycommvp.data.network.response.Product
import com.example.shopsavvycommvp.ui.admin.interactor.AdminActivityMVPInteractor
import com.example.shopsavvycommvp.ui.admin.presenter.AdminActivityMVPPresenter
import com.example.shopsavvycommvp.ui.admindetail.view.AdminDetailActivity
import com.example.shopsavvycommvp.ui.base.view.BaseActivity
import com.example.shopsavvycommvp.ui.main.fragments.homefragment.view.ProductAllAdapter
import com.example.shopsavvycommvp.util.AppConstants
import com.example.shopsavvycommvp.util.ToastUtils
import com.jakewharton.rxbinding.support.v7.widget.RxSearchView
import kotlinx.android.synthetic.main.activity_admin.*
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import java.security.AccessController.getContext
import java.util.*
import javax.inject.Inject

class AdminActivity : BaseActivity(), AdminActivityMVPView, ProductAllAdapter.ClickItemListener {
    override val layoutId: Int
        get() = R.layout.activity_admin

    @Inject
    lateinit var presenter: AdminActivityMVPPresenter<AdminActivityMVPView, AdminActivityMVPInteractor>
    private lateinit var adapterProduct: ProductAllAdapter
    private var idTopProduct: Int? = null
    override fun setUp() {
        presenter.onAttach(this)
        initView()
        vBtnAddProduct.setOnClickListener {
            val intent = Intent(this, AdminDetailActivity::class.java)
            intent.putExtra(AppConstants.AdminDetail.LIST_PRODUCT_SIZE, idTopProduct)
            startActivity(intent)
        }
        imvBack.setOnClickListener {
            finish()
        }
        RxSearchView.queryTextChanges(edtSearchProductAdmin)
            .skip(1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<CharSequence?> {
                    override fun onError(e: Throwable?) {
                    }
                    override fun onNext(t: CharSequence?) {
                        adapterProduct.searchProduct(t.toString())
                    }
                    override fun onCompleted() {
                    }
                })
    }

    @SuppressLint("RestrictedApi")
    private fun showPopUp(product: Product, view: View) {
        val menuBuilder = MenuBuilder(applicationContext)
        val inflater = MenuInflater(applicationContext)
        inflater.inflate(R.menu.menu_option_admin_product, menuBuilder)
        @SuppressLint("RestrictedApi")
        val optionsMenu = MenuPopupHelper(applicationContext, menuBuilder, view)
        optionsMenu.setForceShowIcon(true)
        menuBuilder.setCallback(object : MenuBuilder.Callback {
            override fun onMenuItemSelected(
                menu: MenuBuilder,
                item: MenuItem
            ): Boolean {
                return when (item.itemId) {
                    R.id.actionEditProduct -> {
                        val intent = Intent(this@AdminActivity, AdminDetailActivity::class.java)
                        intent.putExtra(AppConstants.AdminDetail.PRODUCT, product)
                        intent.putExtra(AppConstants.AdminDetail.IS_EDIT_PRODUCT, true)
                        startActivity(intent)
                        true
                    }
                    R.id.actionDelete -> {
                        presenter.removeProduct(product.id)
                        true
                    }
                    else -> false
                }
            }

            override fun onMenuModeChange(menu: MenuBuilder) {}
        })
        optionsMenu.show()
    }

    private fun initView() {
        adapterProduct = ProductAllAdapter(AppConstants.TYPE_PRODUCT_ADMIN)
        adapterProduct.setOnClickListener(this@AdminActivity)
        rcvProductAll.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(this, 2)
        rcvProductAll.layoutManager = layoutManager
        rcvProductAll.adapter = adapterProduct
        presenter.getProductToRcv()

    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun getProductAllFailed(msg: String) {
        this.showError(msg)
    }

    override fun getProductAllSuccess(data: ArrayList<Product>) {
        adapterProduct.clearData()
        adapterProduct.addData(data)
        idTopProduct = data.get(data.size - 1).id
        if (edtSearchProductAdmin.query.toString().trim().isNotEmpty()) {
            adapterProduct.searchProduct(edtSearchProductAdmin.query.toString().trim())
        }
    }

    override fun removeProductFail(msg: String) {
        this.showError(msg)
    }

    override fun removeProductSuccess() {
        ToastUtils.showToast(this, getString(R.string.delete_success))
    }

    override fun mOnItemClickListener(product: Product, view: View) {}

    override fun mOnNoResultListener(isNoResult: Boolean) {
        if (isNoResult) {
            tvNoResultAdmin.visibility = View.VISIBLE
        } else {
            tvNoResultAdmin.visibility = View.GONE
        }
    }

    override fun mOnThreeDotClickListener(product: Product, view: View) {
        showPopUp(product,view)
    }
}