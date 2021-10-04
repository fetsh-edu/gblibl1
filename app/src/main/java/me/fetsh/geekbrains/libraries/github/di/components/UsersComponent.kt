package me.fetsh.geekbrains.libraries.github.di.components

import dagger.BindsInstance
import dagger.Subcomponent
import me.fetsh.geekbrains.libraries.github.di.UsersScope
import me.fetsh.geekbrains.libraries.github.di.UsersScopeContainer
import me.fetsh.geekbrains.libraries.github.di.modules.UsersSubcomponents
import me.fetsh.geekbrains.libraries.github.models.GithubRepoUI
import me.fetsh.geekbrains.libraries.github.ui.screens.users.UsersPresenter

@UsersScope
@Subcomponent(modules = [
    UsersSubcomponents::class
])
interface UsersComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance scopeContainer: UsersScopeContainer): UsersComponent
    }
    fun userComponentFactory() : UserComponent.Factory
    fun repoComponentFactory() : RepoComponent.Factory

    fun usersPresenter() : UsersPresenter
}