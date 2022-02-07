package com.example.stateapp

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import com.example.stateapp.di.component.AppComponent
import com.example.stateapp.di.component.DaggerAppComponent
import com.example.stateapp.di.modules.DatabaseModule
import com.example.stateapp.di.modules.NetworkModule

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .databaseModule(DatabaseModule(applicationContext))
            .networkModule(
                NetworkModule(
                    getSystemService(
                        Context.CONNECTIVITY_SERVICE
                    ) as ConnectivityManager,
                    Toast.makeText(this, "", Toast.LENGTH_LONG)
                )
            )
            .build()
    }
}