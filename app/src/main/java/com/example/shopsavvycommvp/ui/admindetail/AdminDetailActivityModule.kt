package com.example.shopsavvycommvp.ui.admindetail

import com.example.shopsavvycommvp.ui.admin.interactor.AdminActivityInteractor
import com.example.shopsavvycommvp.ui.admin.interactor.AdminActivityMVPInteractor
import com.example.shopsavvycommvp.ui.admin.presenter.AdminActivityMVPPresenter
import com.example.shopsavvycommvp.ui.admin.presenter.AdminActivityPresenter
import com.example.shopsavvycommvp.ui.admin.view.AdminActivityMVPView
import com.example.shopsavvycommvp.ui.admindetail.interactor.AdminDetailInteractor
import com.example.shopsavvycommvp.ui.admindetail.interactor.AdminDetailMVPInteractor
import com.example.shopsavvycommvp.ui.admindetail.presenter.AdminDetailMVPPresenter
import com.example.shopsavvycommvp.ui.admindetail.presenter.AdminDetailPresenter
import com.example.shopsavvycommvp.ui.admindetail.view.AdminDetailMVPView
import dagger.Module
import dagger.Provides

@Module
class AdminDetailActivityModule {
    @Provides
    internal fun provideAdminDetailActivityInteractor(interactor: AdminDetailInteractor): AdminDetailMVPInteractor = interactor
    @Provides
    internal fun provideAdminDetailActivityPresenter(presenter: AdminDetailPresenter<AdminDetailMVPView, AdminDetailMVPInteractor>)
            : AdminDetailMVPPresenter<AdminDetailMVPView, AdminDetailMVPInteractor> = presenter
}