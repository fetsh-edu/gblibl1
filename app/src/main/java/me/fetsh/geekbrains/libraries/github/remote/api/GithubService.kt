package me.fetsh.geekbrains.libraries.github.remote.api

import io.reactivex.rxjava3.core.Single
import me.fetsh.geekbrains.libraries.github.models.GithubUser
import retrofit2.http.GET

interface GithubService {

    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>
}