package com.example.gb_libs.presentation

import com.example.gb_libs.model.GithubUser
import com.example.gb_libs.model.GithubUsersRepo
import com.example.gb_libs.screens.AndroidScreens
import com.example.gb_libs.view.UserItemView
import com.example.gb_libs.view.UsersListView
import com.github.terrakok.cicerone.Router
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

    private val users by lazy {
        usersRepo.getUsers()
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(AndroidScreens.UserScreen(users[itemView.pos].id))
        }
    }

    fun loadData() {
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}