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

    companion object {
        private const val DB_NAME = "database.db"
        private var instance: Database? = null
        fun getInstance() = instance ?: throw RuntimeException("Database has not been created. Please call create(context)")

        fun create(context: Context?) {
            if (instance == null) {
                instance = Room.databaseBuilder(context!!, Database::class.java, DB_NAME)
                    .build()
            }
        }
    }
}