package com.example.gb_libs.presentation

import com.example.gb_libs.model.GithubUsersRepo
import com.example.gb_libs.view.UserView
import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class UserPresenter(
    private val usersRepo: GithubUsersRepo,
    private val router: Router,
    private val userId: String
) : MvpPresenter<UserView>() {

    private val user by lazy {
        usersRepo.find(userId)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init(user)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}