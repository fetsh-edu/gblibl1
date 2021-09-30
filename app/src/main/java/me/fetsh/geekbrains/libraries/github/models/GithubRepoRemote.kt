package me.fetsh.geekbrains.libraries.github.models

import com.google.gson.annotations.Expose
import io.reactivex.rxjava3.core.Single
import me.fetsh.geekbrains.libraries.github.remote.RetrofitHolder
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

data class GithubRepoRemote(
    @Expose
    val id: Long,
    @Expose
    val name: String,
    @Expose
    val description: String? = null,
    @Expose
    val forks: Int
) : ToUIRepoConvertible {
    override fun toUIRepo() : GithubRepoUI {
        return GithubRepoUI(id, name, description, forks)
    }
    fun toDBRepo(userId: Long) : GithubRepoLocal {
        return GithubRepoLocal(id, name, forks, userId)
    }

    class Repo {
        private val apiService : GithubRepoService by lazy {
            RetrofitHolder.retrofit.create(GithubRepoService::class.java)
        }

        fun getReposByLogin(login: String): Single<List<GithubRepoRemote>> =
            apiService
                .getRepos(login)

        fun getReposByURL(url: String) : Single<List<GithubRepoRemote>> =
            apiService
                .getReposByURL(url)

        interface GithubRepoService {
            @GET("/users/{login}/repos")
            fun getRepos(
                @Path("login") login: String
            ): Single<List<GithubRepoRemote>>

            @GET
            fun getReposByURL(@Url url: String): Single<List<GithubRepoRemote>>
        }
    }
}