package me.fetsh.geekbrains.libraries.github.di.modules

import dagger.Module
import me.fetsh.geekbrains.libraries.github.di.components.RepoComponent
import me.fetsh.geekbrains.libraries.github.di.components.UserComponent

@Module(
    subcomponents = [
        UserComponent::class,
        RepoComponent::class
    ]
)
class UsersSubcomponents