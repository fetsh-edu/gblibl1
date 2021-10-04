package me.fetsh.geekbrains.libraries.github

import android.app.Application
import me.fetsh.geekbrains.libraries.github.di.UserScopeContainer
import me.fetsh.geekbrains.libraries.github.di.UsersScopeContainer
import me.fetsh.geekbrains.libraries.github.di.components.*
import me.fetsh.geekbrains.libraries.github.models.GithubUserUI

class App : Application(), UsersScopeContainer, UserScopeContainer {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
    private var usersSubcomponent: UsersComponent? = null
        private set

    private var userSubcomponent: UserComponent? = null
        private set

    fun initUsersSubcomponent() = appComponent.usersComponentFactory().create(this).also {
        usersSubcomponent = it
    }
    fun initUserSubcomponent(githubUserUI: GithubUserUI) = usersSubcomponent?.let { usersComponent ->
        usersComponent.userComponentFactory().create(githubUserUI, this).also { userComponent ->
            userSubcomponent = userComponent
        }
    }

    override fun releaseUsersScope() {
        usersSubcomponent = null
    }
    override fun releaseUserScope() {
        userSubcomponent = null
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: App
    }
}