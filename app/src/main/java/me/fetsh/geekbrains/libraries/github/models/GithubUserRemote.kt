package me.fetsh.geekbrains.libraries.github.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.reactivex.rxjava3.core.Single
import me.fetsh.geekbrains.libraries.github.remote.RetrofitHolder
import retrofit2.http.GET
import retrofit2.http.Path

data class GithubUserRemote (
    @Expose
    val id: Long,

    @Expose
    val login: String,

    @Expose
    @SerializedName("avatar_url")
    val avatarUrl: String,

    @Expose
    @SerializedName("repos_url")
    val reposUrl: String,

    @Expose
    val name: String? = null,

    @Expose
    val followers: Int? = null,

    @Expose
    val following: Int? = null,
) : ToUIUserConvertible {

    fun toDBUser() : GithubUserLocal {
        return GithubUserLocal(id, login, avatarUrl, reposUrl)
    }
    override fun toUIUser() : GithubUserUI {
        return GithubUserUI(id, login, avatarUrl, reposUrl, name, followers, following)
    }

    class Repo {
        interface GithubUserService {

            @GET("/users")
            fun getUsers(): Single<List<GithubUserRemote>>

            @GET("/users/{login}")
            fun getUser(
                @Path("login") login: String
            ): Single<GithubUserRemote>
        }

        private val apiService : GithubUserService by lazy {
            RetrofitHolder.retrofit.create(GithubUserService::class.java)
        }

        fun getUsers(): Single<List<GithubUserRemote>> =
            apiService
                .getUsers()

        fun getUser(login: String): Single<GithubUserRemote> =
            apiService
                .getUser(login)
    }
}