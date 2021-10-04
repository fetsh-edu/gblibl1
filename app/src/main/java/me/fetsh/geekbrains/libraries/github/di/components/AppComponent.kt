package me.fetsh.geekbrains.libraries.github.di.components

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import me.fetsh.geekbrains.libraries.github.di.modules.ApiModule
import me.fetsh.geekbrains.libraries.github.di.modules.AppSubcomponents
import me.fetsh.geekbrains.libraries.github.di.modules.CiceroneModule
import me.fetsh.geekbrains.libraries.github.di.modules.DatabaseModule
import me.fetsh.geekbrains.libraries.github.ui.activity.MainActivity
import me.fetsh.geekbrains.libraries.github.ui.activity.MainPresenter
import me.fetsh.geekbrains.libraries.github.ui.screens.users.UsersPresenter
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        CiceroneModule::class,
        DatabaseModule::class,
        AppSubcomponents::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context) : AppComponent
    }

    fun usersComponentFactory() : UsersComponent.Factory
    fun repoComponentFactory() : RepoComponent.Factory
    fun mainPresenter() : MainPresenter
    fun inject(mainActivity: MainActivity)
}