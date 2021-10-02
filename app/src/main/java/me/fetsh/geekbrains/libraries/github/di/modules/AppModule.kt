package me.fetsh.geekbrains.libraries.github.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import me.fetsh.geekbrains.libraries.github.App
import javax.inject.Singleton

@Module
class AppModule(private val app : App) {
    @Provides
    @Singleton
    fun app() : Context  = app
}