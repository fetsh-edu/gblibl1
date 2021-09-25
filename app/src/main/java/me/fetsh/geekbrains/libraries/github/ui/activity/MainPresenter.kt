package me.fetsh.geekbrains.libraries.github.ui.activity

import com.github.terrakok.cicerone.Router
import me.fetsh.geekbrains.libraries.github.navigation.Screens
import moxy.MvpPresenter

class MainPresenter(private val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.UsersScreen())
    }

    fun backPressed() {
        router.exit()
    }
}