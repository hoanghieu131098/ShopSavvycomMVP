package com.example.shopsavvycommvp.di.component

import android.app.Application
import com.example.shopsavvycommvp.MvpApp
import com.example.shopsavvycommvp.di.builder.ActivityBuilder
import com.example.shopsavvycommvp.di.module.AppModule
import com.example.shopsavvycommvp.di.module.NetworkModule
import dagger.BindsInstance

import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class), (AppModule::class), (ActivityBuilder::class), (NetworkModule::class)])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: MvpApp)
}