package me.fetsh.geekbrains.libraries.github.di.components

import dagger.Component
import me.fetsh.geekbrains.libraries.github.di.modules.ApiModule
import me.fetsh.geekbrains.libraries.github.di.modules.AppModule
import me.fetsh.geekbrains.libraries.github.di.modules.CiceroneModule
import me.fetsh.geekbrains.libraries.github.di.modules.DatabaseModule
import me.fetsh.geekbrains.libraries.github.ui.activity.MainActivity
import me.fetsh.geekbrains.libraries.github.ui.activity.MainPresenter
import me.fetsh.geekbrains.libraries.github.ui.screens.users.UsersPresenter
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ApiModule::class,
        CiceroneModule::class,
        DatabaseModule::class
    ]
)
interface AppComponent {

    val userComponentFactory: UserComponent.Factory
    val repoComponentFactory: RepoComponent.Factory

    fun mainPresenter() : MainPresenter
    fun usersPresenter() : UsersPresenter

    fun inject(mainActivity: MainActivity)
}