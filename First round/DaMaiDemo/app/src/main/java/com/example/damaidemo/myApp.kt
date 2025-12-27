package com.example.damaidemo

import android.app.Application
import android.content.Context

class MyApp : Application() {
    companion object {
        lateinit var appContext: Context // 全局上下文
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }
}