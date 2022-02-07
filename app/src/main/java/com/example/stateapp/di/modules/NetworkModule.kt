package com.example.stateapp.di.modules

import android.net.ConnectivityManager
import android.widget.Toast
import com.example.stateapp.data.api.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule(
    private val connectivityManager: ConnectivityManager,
    private val toast: Toast,
) {

    @Provides
    fun provideRetrofitService(): ApiService? {
        if (isConnected()) {
            toast.setText("Data take from server!")
            toast.show()
            return Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
        toast.setText("Data take from local db!")
        toast.show()
        return null
    }

    private fun isConnected(): Boolean {
        return connectivityManager.activeNetworkInfo != null
    }
}