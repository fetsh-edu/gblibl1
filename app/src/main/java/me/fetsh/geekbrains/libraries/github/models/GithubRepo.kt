package me.fetsh.geekbrains.libraries.github.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.parcelize.Parcelize
import me.fetsh.geekbrains.libraries.github.remote.RetrofitHolder

@Parcelize
data class GithubRepo(
    @Expose
    val id: Long,

    @Expose
    val name: String,

    @Expose
    val description: String? = null,

    @Expose
    val forks: Int

) : Parcelable {
    class Repo {
        fun getRepos(login: String): Single<List<GithubRepo>> =
            RetrofitHolder.apiService
                .getRepos(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}