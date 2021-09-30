package me.fetsh.geekbrains.libraries.github.ui.screens.users

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import me.fetsh.geekbrains.libraries.github.db.Database
import me.fetsh.geekbrains.libraries.github.models.GithubUserLocal
import me.fetsh.geekbrains.libraries.github.models.GithubUserRemote
import me.fetsh.geekbrains.libraries.github.models.GithubUserUI
import me.fetsh.geekbrains.libraries.github.models.ToUIUserConvertible
import me.fetsh.geekbrains.libraries.github.navigation.Screens
import me.fetsh.geekbrains.libraries.github.ui.contracts.RVContract
import me.fetsh.geekbrains.libraries.github.utils.AndroidNetworkStatus
import moxy.MvpPresenter

class UsersPresenter(
    private val networkStatus: AndroidNetworkStatus,
    private val usersRepo: GithubUserRemote.Repo,
    private val router: Router,
    private val db: Database
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
        networkStatus.isOnlineSingle()
            .subscribeOn(Schedulers.io())
            .flatMap { isOnline ->
                when(isOnline) {
                    true -> {
                        usersRepo.getUsers().doAfterSuccess { users ->
                            db.userDao.insert(users.map(GithubUserRemote::toDBUser))
                        }
                    }
                    false -> {
                        db.userDao.getAll()
                    }
                }
            }
            .map{ users -> users.map(ToUIUserConvertible::toUIUser) }
            .observeOn(AndroidSchedulers.mainThread())
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
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}