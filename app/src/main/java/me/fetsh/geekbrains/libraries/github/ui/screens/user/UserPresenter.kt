package me.fetsh.geekbrains.libraries.github.ui.screens.user

import com.github.terrakok.cicerone.Router
import me.fetsh.geekbrains.libraries.github.models.GithubUser
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class UserPresenter(
    private val usersRepo: GithubUser.Repo,
    private val router: Router,
    private val user: GithubUser
) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init(user)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}