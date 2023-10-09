package com.day.myreport

import android.app.Application

class MyReport : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private lateinit var instance: MyReport

        fun getInstance(): MyReport {
            return instance
        }
    }
}