package me.fetsh.geekbrains.libraries.github.remote

import com.google.gson.GsonBuilder
import me.fetsh.geekbrains.libraries.github.remote.api.GithubService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHolder {
    val apiService: GithubService by lazy {
        val gson = GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()

        Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(GithubService::class.java)
    }
}