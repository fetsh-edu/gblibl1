package me.fetsh.geekbrains.libraries.github.di.components

import dagger.BindsInstance
import dagger.Subcomponent
import me.fetsh.geekbrains.libraries.github.models.GithubUserUI
import me.fetsh.geekbrains.libraries.github.ui.screens.user.UserPresenter

@Subcomponent
interface UserComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance user: GithubUserUI): UserComponent
    }

    val presenter: UserPresenter
}