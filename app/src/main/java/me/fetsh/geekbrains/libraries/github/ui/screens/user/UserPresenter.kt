package me.fetsh.geekbrains.libraries.github.ui.screens.user

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.Disposable
import me.fetsh.geekbrains.libraries.github.models.GithubRepo
import me.fetsh.geekbrains.libraries.github.models.GithubUser
import me.fetsh.geekbrains.libraries.github.ui.contracts.RVContract
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class UserPresenter(
    private val reposRepo: GithubRepo.Repo,
    private val usersRepo: GithubUser.Repo,
    private val router: Router,
    private val user: GithubUser,
) : MvpPresenter<UserView>() {

    class ReposListPresenter : RVContract.ReposListPresenter {

        val repos = mutableListOf<GithubRepo>()

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
    }

    private fun loadRepositories() {
        viewState.showLoading()
        reposRepo.getRepos(user.login)
            .subscribe({ repos ->
                reposListPresenter.repos.addAll(repos)
                Log.d("AAA", "REPOS: ${repos.size.toString()}")
                viewState.updateReposList()
            }, {
                Log.e("UsersPresenter", "Failed to load users", it)
            })
    }

    private fun loadUser() {
        subscription = usersRepo.getUser(user.login)
            .subscribe({ user ->
                Log.d("AAA", "USER IS LOADED")
                viewState.showFullUserData(user)
            }, {
                Log.e("UsersPresenter", "Failed to load users", it)
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}