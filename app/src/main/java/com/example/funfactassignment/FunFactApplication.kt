package com.example.funfactassignment

import android.app.Application
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.getValue

class FunFactApplication : Application() {
    //coroutine scope tied to the application lifetime which we can run suspend functions in
    val scope = CoroutineScope(SupervisorJob())

    //get a reference to the DB
    val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            FunFactDatabase::class.java,
            "fun_fact_database"
        ).build()
    }

    //create our repository using lazy to access the DB when we need it
    val funFactRepository by lazy { FactsRepository(scope, db.funFactDao()) }
}