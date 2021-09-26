package me.fetsh.geekbrains.libraries.github.ui.screens.user

import me.fetsh.geekbrains.libraries.github.models.GithubUser
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface UserView : MvpView {
    fun init(user: GithubUser?)
    fun showFullUserData(user: GithubUser)
    fun updateReposList()
    fun showLoading()
}