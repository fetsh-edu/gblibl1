package me.fetsh.geekbrains.libraries.github.models

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Entity
class GithubUserLocal (
    @PrimaryKey var id: Long,
    var login: String,
    var avatarUrl: String,
    var reposUrl: String,
    val name: String? = null,
    val followers: Int? = null,
    val following: Int? = null
) : ToUIUserConvertible {
    override fun toUIUser() : GithubUserUI {
        return GithubUserUI(id, login, avatarUrl, reposUrl)
    }

    @Dao
    interface UserDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insert(user: GithubUserLocal)

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insert(vararg users: GithubUserLocal)

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insert(users: List<GithubUserLocal>) : Completable

        @Update
        fun update(user: GithubUserLocal)

        @Update
        fun update(vararg users: GithubUserLocal)

        @Update
        fun update(users: List<GithubUserLocal>)

        @Delete
        fun delete(user: GithubUserLocal)

        @Delete
        fun delete(vararg users: GithubUserLocal)

        @Delete
        fun delete(users: List<GithubUserLocal>)

        @Query("SELECT * FROM GithubUserLocal")
        fun getAll(): Single<List<GithubUserLocal>>

        @Query("SELECT * FROM GithubUserLocal WHERE login = :login LIMIT 1")
        fun findByLogin(login: String): GithubUserLocal?
    }
}