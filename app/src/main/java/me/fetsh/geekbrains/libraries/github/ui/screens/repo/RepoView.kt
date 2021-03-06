package me.fetsh.geekbrains.libraries.github.ui.screens.repo

import me.fetsh.geekbrains.libraries.github.models.GithubRepoUI
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface RepoView : MvpView {
    fun init(repo: GithubRepoUI)
}