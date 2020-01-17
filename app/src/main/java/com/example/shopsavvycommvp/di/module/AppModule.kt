package com.example.shopsavvycommvp.di.module

import android.app.Application
import android.content.Context
import com.example.shopsavvycommvp.data.preferences.MvpPreferences
import com.example.shopsavvycommvp.util.SchedulerProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context{
        return application
    }

    @Provides
    @Singleton
    fun provideGson(): Gson{
        return GsonBuilder().serializeNulls().create()
    }

    @Provides
    @Singleton
    fun providePrefHelper(context: Context,gson: Gson): MvpPreferences{
        return MvpPreferences(context,gson)
    }

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider {
        return SchedulerProvider()
    }

}