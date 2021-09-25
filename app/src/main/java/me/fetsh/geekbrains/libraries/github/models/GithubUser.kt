package me.fetsh.geekbrains.libraries.github.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.parcelize.Parcelize
import me.fetsh.geekbrains.libraries.github.remote.RetrofitHolder

@Parcelize
data class GithubUser(
    @Expose
    val login: String,

    @Expose
    val id: Long,

    @Expose
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,

    @Expose
    val name: String? = null,

    @Expose
    val followers: Int? = null,

    @Expose
    val following: Int? = null,
) : Parcelable {

    class Repo {

        fun getUsers(): Single<List<GithubUser>> =
            RetrofitHolder.apiService
                .getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

        fun getUser(login: String): Single<GithubUser> =
            RetrofitHolder.apiService
                .getUser(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}