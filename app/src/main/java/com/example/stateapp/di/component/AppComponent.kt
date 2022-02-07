package com.example.stateapp.di.component

import com.example.stateapp.di.modules.DatabaseModule
import com.example.stateapp.di.modules.NetworkModule
import com.example.stateapp.presentation.screens.MainActivity
import dagger.Component

@Component(modules = [DatabaseModule::class, NetworkModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}