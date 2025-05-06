package com.example.viewmodeltest

import android.app.Application
import com.example.viewmodeltest.data.AppContainer
import com.example.viewmodeltest.data.DefaultAppContainer

class DogsListApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(applicationContext)
    }
}