package me.fetsh.geekbrains.libraries.github.models

import android.os.Parcelable
import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.reactivex.rxjava3.core.Single
import kotlinx.parcelize.Parcelize
import me.fetsh.geekbrains.libraries.github.remote.RetrofitHolder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

@Parcelize
data class GithubUser(
    @Expose
    val login: String? = null,

    @Expose
    val id: Long? = null,

    @Expose
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
) : Parcelable {

    class Repo {

        fun getUsers() = RetrofitHolder.apiService.getUsers()

    }

}