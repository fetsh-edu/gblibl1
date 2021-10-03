package me.fetsh.geekbrains.libraries.github.ui.screens.repo

import android.util.Log
import com.github.terrakok.cicerone.Router
import me.fetsh.geekbrains.libraries.github.models.GithubRepoUI
import me.fetsh.geekbrains.libraries.github.models.RepoInterpolator
import moxy.MvpPresenter
import javax.inject.Inject

class RepoPresenter @Inject constructor(
    private val router: Router,
    private val repo: GithubRepoUI
) : MvpPresenter<RepoView>() {

    @Inject
    lateinit var repoInterpolator : RepoInterpolator

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init(repo)
        if (this::repoInterpolator.isInitialized) {
            Log.wtf("Interpolated", "${repoInterpolator.interpolate(repo)}")
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}