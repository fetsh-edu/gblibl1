package me.fetsh.geekbrains.libraries.github.models

import android.os.Parcelable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.parcelize.Parcelize
import me.fetsh.geekbrains.libraries.github.db.Database
import me.fetsh.geekbrains.libraries.github.utils.NetworkStatus
import javax.inject.Inject

@Parcelize
data class GithubRepoUI(
    val id: Long,
    val name: String,
    val description: String? = null,
    val forks: Int
) : Parcelable {

    class Repo @Inject constructor(
        private val networkStatus: NetworkStatus,
        private val reposRepo : GithubRepoRemote.Repo,
        private val db : Database

    ) {
        fun getRepos(user : GithubUserUI) : Single<List<GithubRepoUI>> {
            return networkStatus.isOnlineSingle()
                .subscribeOn(Schedulers.io())
                .flatMap { isOnline ->
                    when (isOnline) {
                        true -> {
                            reposRepo.getReposByURL(user.reposUrl).doAfterSuccess{ repos ->
                                db.repositoryDao.insert(repos.map { repo -> repo.toDBRepo(user.id) } )
                            }
                        }
                        false -> {
                            db.repositoryDao.findForUser(user.id)
                        }
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .map { users -> users.map(ToUIRepoConvertible::toUIRepo) }
        }
    }

}