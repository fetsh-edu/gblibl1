package me.fetsh.geekbrains.libraries.github.ui.screens.repo

import com.github.terrakok.cicerone.Router
import me.fetsh.geekbrains.libraries.github.models.GithubRepo
import moxy.MvpPresenter

class RepoPresenter(
    private val router: Router,
    private val repo: GithubRepo
) : MvpPresenter<RepoView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init(repo)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}