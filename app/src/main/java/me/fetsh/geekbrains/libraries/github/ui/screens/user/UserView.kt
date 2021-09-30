package me.fetsh.geekbrains.libraries.github.ui.screens.user

import me.fetsh.geekbrains.libraries.github.models.GithubUserUI
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface UserView : MvpView {
    fun init(user: GithubUserUI?)
    fun showFullUserData(user: GithubUserUI)
    fun updateReposList()
    fun showLoading()
}