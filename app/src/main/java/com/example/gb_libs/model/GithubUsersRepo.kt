package com.example.gb_libs.model

import io.reactivex.rxjava3.core.Observable

class GithubUsersRepo {

    private val users = listOf(
        GithubUser("user1", "user1"),
        GithubUser("user2", "user2"),
        GithubUser("user3", "user3"),
        GithubUser("user4", "user4"),
        GithubUser("user5", "user5")
    )

    fun getUsers(): Observable<GithubUser> =
        Observable.fromIterable(users)

    fun find(userId: String) : GithubUser? {
        return users.find { user -> user.id == userId }
    }
}