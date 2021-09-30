package me.fetsh.geekbrains.libraries.github.models

import androidx.room.*
import io.reactivex.rxjava3.core.Single

@Entity(
    foreignKeys = [ForeignKey(
        entity = GithubUserLocal::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
class GithubRepoLocal(
    @PrimaryKey var id: Long,
    var name: String,
    var forks: Int,
    var userId: Long
) : ToUIRepoConvertible {
    override fun toUIRepo() : GithubRepoUI {
        return GithubRepoUI(id, name, null, forks)
    }

    @Dao
    interface RepositoryDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insert(repo: GithubRepoLocal)

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insert(vararg repos: GithubRepoLocal)

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insert(repos: List<GithubRepoLocal>)

        @Update
        fun update(repo: GithubRepoLocal)

        @Update
        fun update(vararg repos: GithubRepoLocal)

        @Update
        fun update(repos: List<GithubRepoLocal>)

        @Delete
        fun delete(repo: GithubRepoLocal)

        @Delete
        fun delete(vararg repos: GithubRepoLocal)

        @Delete
        fun delete(repos: List<GithubRepoLocal>)

        @Query("SELECT * FROM GithubRepoLocal")
        fun getAll(): List<GithubRepoLocal>

        @Query("SELECT * FROM GithubRepoLocal WHERE userId = :userId")
        fun findForUser(userId: Long): Single<List<GithubRepoLocal>>
    }
}