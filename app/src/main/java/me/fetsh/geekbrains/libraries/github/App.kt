package me.fetsh.geekbrains.libraries.github

import android.app.Application
import me.fetsh.geekbrains.libraries.github.di.modules.AppComponent
import me.fetsh.geekbrains.libraries.github.di.modules.AppModule
import me.fetsh.geekbrains.libraries.github.di.modules.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {

        lateinit var instance: App
    }
}