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
data class GithubUserUI(
    val id: Long,
    val login: String,
    val avatarUrl: String,
    val reposUrl: String,
    val name: String? = null,
    val followers: Int? = null,
    val following: Int? = null
) : Parcelable {

    class Repo @Inject constructor(
        private val networkStatus: NetworkStatus,
        private val remoteRepo:  GithubUserRemote.Repo,
        private val db: Database
    ) {
        fun getUsers() : Single<List<GithubUserUI>> {
            return networkStatus.isOnlineSingle()
                .subscribeOn(Schedulers.io())
                .flatMap { isOnline ->
                    when(isOnline) {
                        true -> {
                            remoteRepo.getUsers().doAfterSuccess { users ->
                                db.userDao.insert(users.map(GithubUserRemote::toDBUser))
                            }
                        }
                        false -> {
                            db.userDao.getAll()
                        }
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .map{ users -> users.map(ToUIUserConvertible::toUIUser) }
        }
    }
}