package me.fetsh.geekbrains.libraries.github.di.modules

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import me.fetsh.geekbrains.libraries.github.utils.AndroidNetworkStatus
import me.fetsh.geekbrains.libraries.github.utils.NetworkStatus
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    @Named("baseUrl")
    fun baseUrl() : String = "https://api.github.com"

    @Provides
    @Singleton
    fun gson() : Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Provides
    @Singleton
    fun retrofitHolder(gson: Gson, @Named("baseUrl") baseUrl : String) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun networkStatus(context : Context) : NetworkStatus = AndroidNetworkStatus(context)
}