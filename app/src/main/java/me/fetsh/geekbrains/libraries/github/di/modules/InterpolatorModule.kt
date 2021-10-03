package me.fetsh.geekbrains.libraries.github.di.modules

import dagger.Module
import dagger.Provides
import me.fetsh.geekbrains.libraries.github.di.RepoScope
import me.fetsh.geekbrains.libraries.github.models.FancyRepoInterpolator
import me.fetsh.geekbrains.libraries.github.models.RepoInterpolator

@Module
class InterpolatorModule {

    @Provides
    @RepoScope
    fun provideInterpolator() : RepoInterpolator = FancyRepoInterpolator()
}