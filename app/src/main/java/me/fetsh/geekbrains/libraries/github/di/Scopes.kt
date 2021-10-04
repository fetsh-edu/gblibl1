package me.fetsh.geekbrains.libraries.github.di

import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class UsersScope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class UserScope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class RepoScope

interface UsersScopeContainer {
    fun releaseUsersScope()
}
interface UserScopeContainer {
    fun releaseUserScope()
}
interface RepoScopeContainer {
    fun releaseRepoScope()
}