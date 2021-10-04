package me.fetsh.geekbrains.libraries.github.di.modules

import dagger.Module
import me.fetsh.geekbrains.libraries.github.di.components.UsersComponent

@Module(
    subcomponents = [
        UsersComponent::class
    ]
)
class AppSubcomponents