package com.feeds

import android.app.Application

class FeedsApp: Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
    companion object {
        lateinit var instance: FeedsApp
            private set
    }
}