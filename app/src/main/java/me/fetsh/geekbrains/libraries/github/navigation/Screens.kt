package me.fetsh.geekbrains.libraries.github.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import me.fetsh.geekbrains.libraries.github.models.GithubUser
import me.fetsh.geekbrains.libraries.github.ui.screens.user.UserFragment
import me.fetsh.geekbrains.libraries.github.ui.screens.users.UsersFragment


object Screens {

    fun UsersScreen() = FragmentScreen { UsersFragment() }

    fun UserScreen(user: GithubUser) =
        FragmentScreen("User_$user.id") {
            UserFragment.newInstance(user)
        }
}