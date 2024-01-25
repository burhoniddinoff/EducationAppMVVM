package com.example.educationappmvvm.presenter.app

import android.app.Application
import android.media.tv.interactive.AppLinkInfo
import com.example.educationappmvvm.presenter.data.source.MyDatabase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        MyDatabase.init(this)
    }

}