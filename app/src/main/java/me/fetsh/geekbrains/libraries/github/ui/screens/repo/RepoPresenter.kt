package me.fetsh.geekbrains.libraries.github.ui.screens.repo

import com.github.terrakok.cicerone.Router
import me.fetsh.geekbrains.libraries.github.models.GithubRepoUI
import moxy.MvpPresenter
import javax.inject.Inject

class RepoPresenter @Inject constructor(
    private val router: Router,
    private val repo: GithubRepoUI
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