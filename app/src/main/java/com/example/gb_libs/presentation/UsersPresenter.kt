package com.example.gb_libs.presentation

import com.example.gb_libs.model.GithubUser
import com.example.gb_libs.model.GithubUsersRepo
import com.example.gb_libs.screens.AndroidScreens
import com.example.gb_libs.view.UserItemView
import com.example.gb_libs.view.UsersListView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class UsersPresenter(
    private val usersRepo: GithubUsersRepo,
    private val router: Router
) : MvpPresenter<UsersListView>() {

    class UsersListPresenter : UserListPresenter {

        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount(): Int = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()
    private var subscription : Disposable? = null

    private val usersObserver = object : Observer<GithubUser> {
        override fun onSubscribe(d: Disposable) {
            usersListPresenter.users.clear()
            subscription = d
        }

        override fun onNext(user: GithubUser) {
            usersListPresenter.users.add(user)
        }

        override fun onError(e: Throwable) {}

        override fun onComplete() {
            viewState.updateList()
        }

    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        usersRepo.getUsers().subscribe(usersObserver)

        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(AndroidScreens.UserScreen(usersListPresenter.users[itemView.pos].id))
        }
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