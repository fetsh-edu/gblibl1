package me.fetsh.geekbrains.libraries.github.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import me.fetsh.geekbrains.libraries.github.models.GithubRepoUI
import me.fetsh.geekbrains.libraries.github.models.GithubUserUI
import me.fetsh.geekbrains.libraries.github.ui.screens.repo.RepoFragment
import me.fetsh.geekbrains.libraries.github.ui.screens.user.UserFragment
import me.fetsh.geekbrains.libraries.github.ui.screens.users.UsersFragment


object Screens {

    fun UsersScreen() = FragmentScreen { UsersFragment() }

    fun UserScreen(user: GithubUserUI) =
        FragmentScreen("User_${user.id}") {
            UserFragment.newInstance(user)
        }
    fun RepoScreen(repo: GithubRepoUI) =
        FragmentScreen("Repo_${repo.id}") {
            RepoFragment.newInstance(repo)
        }
}