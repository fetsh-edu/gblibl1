package me.fetsh.geekbrains.libraries.github.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import me.fetsh.geekbrains.libraries.github.models.GithubRepo
import me.fetsh.geekbrains.libraries.github.models.GithubUser
import me.fetsh.geekbrains.libraries.github.ui.screens.repo.RepoFragment
import me.fetsh.geekbrains.libraries.github.ui.screens.user.UserFragment
import me.fetsh.geekbrains.libraries.github.ui.screens.users.UsersFragment


object Screens {

    fun UsersScreen() = FragmentScreen { UsersFragment() }

    fun UserScreen(user: GithubUser) =
        FragmentScreen("User_${user.id}") {
            UserFragment.newInstance(user)
        }
    fun RepoScreen(repo: GithubRepo) =
        FragmentScreen("Repo_${repo.id}") {
            RepoFragment.newInstance(repo)
        }
}