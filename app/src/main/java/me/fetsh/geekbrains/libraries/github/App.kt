package me.fetsh.geekbrains.libraries.github

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import me.fetsh.geekbrains.libraries.github.db.Database

class App : Application() {

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }
    val navigationHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router

    override fun onCreate() {
        super.onCreate()
        instance = this
        Database.create(this)
    }

    companion object {

        lateinit var instance: App
    }
}