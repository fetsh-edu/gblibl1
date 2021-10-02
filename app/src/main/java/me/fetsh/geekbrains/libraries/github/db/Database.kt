package me.fetsh.geekbrains.libraries.github.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import me.fetsh.geekbrains.libraries.github.models.GithubRepoLocal
import me.fetsh.geekbrains.libraries.github.models.GithubUserLocal

@androidx.room.Database(
    entities = [GithubUserLocal::class, GithubRepoLocal::class],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract val userDao: GithubUserLocal.UserDao
    abstract val repositoryDao: GithubRepoLocal.RepositoryDao
}