package com.example.shopsavvycommvp.ui.admindetail.view

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.shopsavvycommvp.R
import com.example.shopsavvycommvp.data.network.response.Category
import com.example.shopsavvycommvp.data.network.response.Product
import com.example.shopsavvycommvp.ui.admindetail.interactor.AdminDetailMVPInteractor
import com.example.shopsavvycommvp.ui.admindetail.presenter.AdminDetailMVPPresenter
import com.example.shopsavvycommvp.ui.base.view.BaseActivity
import com.example.shopsavvycommvp.util.AppConstants
import com.example.shopsavvycommvp.util.DialogUtils
import com.example.shopsavvycommvp.util.extension.addTextColorSpan
import com.example.shopsavvycommvp.util.extension.loadImg
import com.tbruyelle.rxpermissions2.RxPermissions
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.android.synthetic.main.activity_admin_detail.*
import javax.inject.Inject

class AdminDetailActivity : BaseActivity(), AdminDetailMVPView {
    override val layoutId: Int
        get() = R.layout.activity_admin_detail
    companion object {
        const val TAG = "AdminDetail"
    }
    @Inject
    lateinit var presenter: AdminDetailMVPPresenter<AdminDetailMVPView, AdminDetailMVPInteractor>
    private var isEditProduct = false
    private var mProduct: Product? = null
    private var mSizeListProduct = 0
    private var listCategory = arrayListOf<Category>()
    private var choosedCategory: Category? = null
    val PICK_IMAGE: Int = 8008
    var pathImage: Uri? = null

    override fun setUp() {
        presenter.onAttach(this)
        presenter.getAllCategory()
        initViews()
        setOnClick()
    }

    private fun setOnClick() {
        imvBack.setOnClickListener {
            finish()
        }
        rlChoiseCategory.setOnClickListener {
            DialogUtils.showCategoryAlertDialog(
                this,
                listCategory,
                object : DialogUtils.ChooseCategory {
                    override fun chooseCategoryListener(category: Category) {
                        choosedCategory = category
                        tvCategoryProduct.setText(choosedCategory?.name)
                    }
                }).show()
        }

        imvChooseImvCreate.setOnClickListener {
            RxPermissions(this)
                .request(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                .subscribe { granted ->
                    if (granted) {
                        val choosePictureIntent =
                            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                        choosePictureIntent.type = "image/*"
                        startActivityForResult(
                            Intent.createChooser(
                                choosePictureIntent,
                                getString(R.string.select_picture)
                            ), PICK_IMAGE
                        )
                    }
                }
        }

        btnAdminDetail.setOnClickListener {
            if (checkValidate()) {
                val nameProduct = edtNameProduct.text.toString().trim()
                val descriptionProduct = edtDescriptionProduct.text.toString().trim()
                val priceProduct = edtPriceProduct.text.toString().trim()
               if (isEditProduct) {
                   mProduct?.name = nameProduct
                   mProduct?.description = descriptionProduct
                   mProduct?.price = priceProduct.toFloat()
                   mProduct?.idCategory = (choosedCategory?.id ?: mProduct?.idCategory) as Int
                   if (pathImage == null) {
                       mProduct?.let { it1 -> presenter.updateProduct(null, it1) }
                   } else {
                       pathImage?.let { it1 -> mProduct?.let { it2 -> presenter.updateProduct(it1, it2) } }
                   }
               } else {
                    val product = Product()
                   product.name = nameProduct
                   product.description = descriptionProduct
                   product.price = priceProduct.toFloat()
                   product.idCategory = (choosedCategory?.id ?: mProduct?.idCategory) as Int
                   product.id = mSizeListProduct + 1
                   pathImage?.let { it1 -> presenter.updateProduct(it1, product) }
               }
            }
        }

    }

    private fun checkValidate(): Boolean {
        return if (edtNameProduct.text.toString().trim().isEmpty()) {
            this.showError("Làm ơn hãy nhập tên sản phẩm!")
            false
        } else if (edtDescriptionProduct.text.toString().trim().isEmpty()) {
            this.showError("Làm ơn hãy nhập thông tin sản phẩm!")
            false
        } else if (edtPriceProduct.text.toString().trim().isEmpty()) {
            this.showError("Làm ơn hãy nhập giá sản phẩm!")
            false
        } else if (tvCategoryProduct.text.toString().trim().isEmpty()) {
            this.showError("Làm ơn hãy chọn loại sản phẩm!")
            false
        } else if (imvChooseImvCreate.drawable.constantState == resources.getDrawable(R.drawable.ic_library_add).constantState) {
            this.showError("Làm ơn chọn ảnh cho sản phẩm!")
            false
        } else {
            true
        }
    }

    private fun initViews() {
        isEditProduct = intent.getBooleanExtra(AppConstants.AdminDetail.IS_EDIT_PRODUCT, false)
        if (isEditProduct) {
            mProduct = intent.getSerializableExtra(AppConstants.AdminDetail.PRODUCT) as Product?
            tvTitleAdminDetail.text = getString(R.string.lable_cap_nhat_san_pham)
            mProduct?.let {
                edtNameProduct.setText(it.name)
                edtPriceProduct.setText(it.price.toString())
                edtDescriptionProduct.setText(it.description)
                imvChooseImvCreate.loadImg(it.dataimage?.get(0))
            }
        } else {
            tvTitleAdminDetail.text = getString(R.string.lable_them_san_pham)
            mSizeListProduct = intent.getIntExtra(AppConstants.AdminDetail.LIST_PRODUCT_SIZE, 0)
        }

        tvNameProductLabel.addTextColorSpan(" *")
        tvDescriptionProductLabel.addTextColorSpan(" *")
        tvPriceProductLabel.addTextColorSpan(" *")
        tvCategoryProductLabel.addTextColorSpan(" *")
        tvImageProductLabel.addTextColorSpan(" *")
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun getAllCategorySuccess(data: ArrayList<Category>) {
        listCategory.addAll(data)
        if (isEditProduct) {
            tvCategoryProduct.setText(getNameCategoryFollowId(mProduct?.idCategory ?: 1, listCategory))
        }
    }

    fun getNameCategoryFollowId(idCategory: Int, list: ArrayList<Category>): String {
        return list.firstOrNull { it.id == idCategory }?.name.toString()
    }

    override fun getAllCategoryFail(msg: String) {
        this.showError(msg)
    }

    override fun haveError(mes: String) {
        this.showError(mes)
    }

    override fun updateProductSuccess() {
        finish()
        Toast.makeText(this,"successfully!",Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK) {
            if (requestCode == PICK_IMAGE && null != data) {
                data.data?.let { beginCrop(it) }
            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && null != data) {
                if (resultCode == AppCompatActivity.RESULT_OK) {
                    pathImage = CropImage.getActivityResult(data).uri
                    Glide.with(this).load(pathImage)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true).centerCrop().into(imvChooseImvCreate)
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, CropImage.getActivityResult(data).error.message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private fun beginCrop(source: Uri) {
        this.let {
            CropImage.activity(source).start(this)
        }
    }
}