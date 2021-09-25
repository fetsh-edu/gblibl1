package me.fetsh.geekbrains.libraries.github.remote.api

import io.reactivex.rxjava3.core.Single
import me.fetsh.geekbrains.libraries.github.models.GithubRepo
import me.fetsh.geekbrains.libraries.github.models.GithubUser
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>

    @GET("/users/{login}")
    fun getUser(
        @Path("login") login: String
    ): Single<GithubUser>

    @GET("/users/{login}/repos")
    fun getRepos(
        @Path("login") login: String
    ): Single<List<GithubRepo>>
}