package me.fetsh.geekbrains.libraries.github.di.modules

import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent
import me.fetsh.geekbrains.libraries.github.models.GithubRepoUI
import me.fetsh.geekbrains.libraries.github.models.GithubUserUI
import me.fetsh.geekbrains.libraries.github.ui.activity.MainActivity
import me.fetsh.geekbrains.libraries.github.ui.activity.MainPresenter
import me.fetsh.geekbrains.libraries.github.ui.screens.repo.RepoPresenter
import me.fetsh.geekbrains.libraries.github.ui.screens.user.UserPresenter
import me.fetsh.geekbrains.libraries.github.ui.screens.users.UsersPresenter
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ApiModule::class,
        CiceroneModule::class,
        DatabaseModule::class
//        RepoModule::class
    ]
)
interface AppComponent {

    val userComponentFactory: UserComponent.Factory
    val repoComponentFactory: RepoComponent.Factory

    fun mainPresenter() : MainPresenter
    fun usersPresenter() : UsersPresenter

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
}

@Subcomponent
interface UserComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance user: GithubUserUI): UserComponent
    }

    val presenter: UserPresenter
}

@Subcomponent
interface RepoComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance user: GithubRepoUI): RepoComponent
    }

    val presenter: RepoPresenter
}