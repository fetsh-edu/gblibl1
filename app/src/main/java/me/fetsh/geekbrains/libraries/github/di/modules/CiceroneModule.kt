package me.fetsh.geekbrains.libraries.github.di.modules

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CiceroneModule {

    private val cicerone : Cicerone<Router> = Cicerone.create()

    @Provides
    @Singleton
    fun cicerone() : Cicerone<Router> = cicerone

    @Provides
    @Singleton
    fun navigatorHolder() : NavigatorHolder = cicerone.getNavigatorHolder()

    @Provides
    @Singleton
    fun router() : Router = cicerone.router
}