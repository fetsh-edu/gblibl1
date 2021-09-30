package me.fetsh.geekbrains.libraries.github.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUserUI(
    val id: Long,
    val login: String,
    val avatarUrl: String,
    val reposUrl: String,
    val name: String? = null,
    val followers: Int? = null,
    val following: Int? = null
) : Parcelable