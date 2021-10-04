package me.fetsh.geekbrains.libraries.github

import android.app.Application
import me.fetsh.geekbrains.libraries.github.di.RepoScopeContainer
import me.fetsh.geekbrains.libraries.github.di.UserScopeContainer
import me.fetsh.geekbrains.libraries.github.di.UsersScopeContainer
import me.fetsh.geekbrains.libraries.github.di.components.*
import me.fetsh.geekbrains.libraries.github.models.GithubRepoUI
import me.fetsh.geekbrains.libraries.github.models.GithubUserUI

class App : Application(), UsersScopeContainer, UserScopeContainer, RepoScopeContainer {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
    var usersSubcomponent: UsersComponent? = null
        private set

    var userSubcomponent: UserComponent? = null
        private set

    var repoSubcomponent: RepoComponent? = null
        private set

    fun initUsersSubcomponent() = appComponent.usersComponentFactory().create(this).also {
        usersSubcomponent = it
    }
    fun initUserSubcomponent(githubUserUI: GithubUserUI) = usersSubcomponent?.let { usersComponent ->
        usersComponent.userComponentFactory().create(githubUserUI, this).also { userComponent ->
            userSubcomponent = userComponent
        }
    }
    fun initRepoSubcomponent(githubRepoUI: GithubRepoUI) = usersSubcomponent?.let { usersComponent ->
        usersComponent.repoComponentFactory().create(githubRepoUI, this).also { userComponent ->
            repoSubcomponent = userComponent
        }
    }

    override fun releaseUsersScope() {
        usersSubcomponent = null
    }
    override fun releaseUserScope() {
        userSubcomponent = null
    }
    override fun releaseRepoScope() {
        repoSubcomponent = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: App
    }
}