package me.fetsh.geekbrains.libraries.github.ui.screens.users

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.Disposable
import me.fetsh.geekbrains.libraries.github.di.UsersScope
import me.fetsh.geekbrains.libraries.github.di.UsersScopeContainer
import me.fetsh.geekbrains.libraries.github.models.GithubUserUI
import me.fetsh.geekbrains.libraries.github.navigation.Screens
import me.fetsh.geekbrains.libraries.github.ui.contracts.RVContract
import moxy.MvpPresenter
import javax.inject.Inject

@UsersScope
class UsersPresenter @Inject constructor(
    private val usersRepo: GithubUserUI.Repo,
    private val router: Router,
    private val usersScopeContainer: UsersScopeContainer
) : MvpPresenter<UsersView>() {

    class UsersListPresenter : RVContract.UserListPresenter {

        val users = mutableListOf<GithubUserUI>()

        override var itemClickListener: ((RVContract.UserItemView) -> Unit)? = null

        override fun getCount(): Int = users.size

        override fun bindView(view: RVContract.UserItemView) {
            val user = users[view.pos]
            view.showLogin(user.login)
            view.loadAvatar(user.avatarUrl.orEmpty())
        }
    }

    val usersListPresenter = UsersListPresenter()
    private var subscription : Disposable? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        loadUsers()

        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(Screens.UserScreen(usersListPresenter.users[itemView.pos]))
        }
    }

    private fun loadUsers() {
        usersRepo.getUsers()
            .subscribe({ users ->
                usersListPresenter.users.addAll(users)
                viewState.updateList()
            }, {
                Log.e("UsersPresenter", "Failed to load users", it)
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        subscription?.dispose()
        usersScopeContainer.releaseUsersScope()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}