package me.fetsh.geekbrains.libraries.github.ui.screens.user

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import me.fetsh.geekbrains.libraries.github.db.Database
import me.fetsh.geekbrains.libraries.github.di.UserScope
import me.fetsh.geekbrains.libraries.github.di.UserScopeContainer
import me.fetsh.geekbrains.libraries.github.models.*
import me.fetsh.geekbrains.libraries.github.navigation.Screens
import me.fetsh.geekbrains.libraries.github.ui.contracts.RVContract
import me.fetsh.geekbrains.libraries.github.utils.AndroidNetworkStatus
import me.fetsh.geekbrains.libraries.github.utils.NetworkStatus
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@UserScope
@InjectViewState
class UserPresenter @Inject constructor(
    private val usersRepo: GithubUserRemote.Repo,
    private val router: Router,
    private val user: GithubUserUI,
    private val reposRepo : GithubRepoUI.Repo,
    private val userScopeContainer: UserScopeContainer
) : MvpPresenter<UserView>() {

    class ReposListPresenter : RVContract.ReposListPresenter {

        val repos = mutableListOf<GithubRepoUI>()

        override var itemClickListener: ((RVContract.RepoItemView) -> Unit)? = null

        override fun getCount(): Int = repos.size

        override fun bindView(view: RVContract.RepoItemView) {
            val repo = repos[view.pos]
            view.showName(repo.name)
        }
    }
    val reposListPresenter = ReposListPresenter()
    private var subscription : Disposable? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init(user)
        loadUser()
        loadRepositories()
        reposListPresenter.itemClickListener = { itemView ->
            router.navigateTo(Screens.RepoScreen(reposListPresenter.repos[itemView.pos]))
        }
    }

    private fun loadRepositories() {
        viewState.showLoading()
        reposRepo.getRepos(user)
            .subscribe({ repos ->
                reposListPresenter.repos.addAll(repos)
                viewState.updateReposList()
            }, {
                Log.e("UserPresenter", "Failed to load repos", it)
            })
    }

    private fun loadUser() {
        subscription = usersRepo.getUser(user.login)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map(GithubUserRemote::toUIUser)
            .subscribe({ user ->
                viewState.showFullUserData(user)
            }, {
                Log.e("UserPresenter", "Failed to load user", it)
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        subscription?.dispose()
        userScopeContainer.releaseUserScope()
    }
}