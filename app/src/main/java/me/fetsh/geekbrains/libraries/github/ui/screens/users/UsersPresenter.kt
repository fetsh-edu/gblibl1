package me.fetsh.geekbrains.libraries.github.ui.screens.users

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import me.fetsh.geekbrains.libraries.github.models.GithubUser
import me.fetsh.geekbrains.libraries.github.navigation.Screens
import me.fetsh.geekbrains.libraries.github.ui.contracts.RVContract
import moxy.MvpPresenter

class UsersPresenter(
    private val usersRepo: GithubUser.Repo,
    private val router: Router,
) : MvpPresenter<UsersView>() {

    class UsersListPresenter : RVContract.UserListPresenter {

        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((RVContract.UserItemView) -> Unit)? = null

        override fun getCount(): Int = users.size

        override fun bindView(view: RVContract.UserItemView) {
            val user = users[view.pos]
            view.showLogin(user.login.orEmpty())
            view.loadAvatar(user.avatarUrl.orEmpty())
        }
    }

    val usersListPresenter = UsersListPresenter()
    private var subscription : Disposable? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(Screens.UserScreen(usersListPresenter.users[itemView.pos]))
        }
    }

    private fun loadData() {
        subscription = usersRepo.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ users ->
                usersListPresenter.users.addAll(users)
                viewState.updateList()
            }, {
                Log.e("UsersPresenter", "Ошибка получения пользователей", it)
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