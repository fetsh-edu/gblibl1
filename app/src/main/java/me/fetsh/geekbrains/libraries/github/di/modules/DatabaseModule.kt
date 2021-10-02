package me.fetsh.geekbrains.libraries.github.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import me.fetsh.geekbrains.libraries.github.db.Database
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun db(context : Context) : Database =
        Room
            .databaseBuilder(context, Database::class.java, DB_NAME)
            .build()

    companion object {
        private const val DB_NAME = "database.db"
    }
}