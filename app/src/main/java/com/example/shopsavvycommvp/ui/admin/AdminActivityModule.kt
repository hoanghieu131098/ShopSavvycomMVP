package com.example.shopsavvycommvp.ui.admin

import com.example.shopsavvycommvp.ui.admin.interactor.AdminActivityInteractor
import com.example.shopsavvycommvp.ui.admin.interactor.AdminActivityMVPInteractor
import com.example.shopsavvycommvp.ui.admin.presenter.AdminActivityMVPPresenter
import com.example.shopsavvycommvp.ui.admin.presenter.AdminActivityPresenter
import com.example.shopsavvycommvp.ui.admin.view.AdminActivityMVPView
import dagger.Module
import dagger.Provides

@Module
class AdminActivityModule {
    @Provides
    internal fun provideAdminActivityInteractor(interactor: AdminActivityInteractor): AdminActivityMVPInteractor = interactor
    @Provides
    internal fun provideAdminActivityPresenter(presenter: AdminActivityPresenter<AdminActivityMVPView, AdminActivityMVPInteractor>)
            : AdminActivityMVPPresenter<AdminActivityMVPView,AdminActivityMVPInteractor> = presenter

}