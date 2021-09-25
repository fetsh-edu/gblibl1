package me.fetsh.geekbrains.libraries.github.models

import android.os.Parcelable
import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.reactivex.rxjava3.core.Single
import kotlinx.parcelize.Parcelize
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

        fun getUsers() = API_SERVICE.getUsers()

        interface ApiService {

            @GET("/users")
            fun getUsers(): Single<List<GithubUser>>
        }

        companion object {
            val API_SERVICE: ApiService by lazy {
                val gson = GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .create()

                Retrofit.Builder()
                    .baseUrl("https://api.github.com")
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                    .create(ApiService::class.java)
            }
        }
    }

}