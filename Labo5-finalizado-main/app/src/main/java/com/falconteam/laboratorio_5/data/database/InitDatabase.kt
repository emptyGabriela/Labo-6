package com.falconteam.laboratorio_5.data.database

import android.app.Application
import androidx.room.Room

class InitDatabase : Application() {
    companion object {
        lateinit var database: PostDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            PostDatabase::class.java,
            "post_database"
        ).fallbackToDestructiveMigration().build()
    }
}