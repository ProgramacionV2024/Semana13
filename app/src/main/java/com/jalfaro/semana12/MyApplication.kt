package com.jalfaro.semana12

import android.app.Application
import com.jalfaro.semana12.database.MyDatabase

class MyApplication: Application() {
    lateinit var database: MyDatabase
    override fun onCreate() {
        super.onCreate()
        database = MyDatabase.buildDatabase(this)
    }
}