package me.fetsh.geekbrains.libraries.github.ui.activity

import com.github.terrakok.cicerone.Router
import me.fetsh.geekbrains.libraries.github.navigation.Screens
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter @Inject constructor(private val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.UsersScreen())
    }

    fun backPressed() {
        router.exit()
    }
}