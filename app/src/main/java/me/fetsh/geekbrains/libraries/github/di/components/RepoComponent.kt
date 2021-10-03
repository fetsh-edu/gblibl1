package me.fetsh.geekbrains.libraries.github.di.components

import dagger.BindsInstance
import dagger.Subcomponent
import me.fetsh.geekbrains.libraries.github.di.RepoScope
import me.fetsh.geekbrains.libraries.github.di.modules.InterpolatorModule
import me.fetsh.geekbrains.libraries.github.models.GithubRepoUI
import me.fetsh.geekbrains.libraries.github.ui.screens.repo.RepoPresenter

@RepoScope
@Subcomponent(modules = [InterpolatorModule::class])
interface RepoComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance user: GithubRepoUI): RepoComponent
    }

    val presenter: RepoPresenter
}