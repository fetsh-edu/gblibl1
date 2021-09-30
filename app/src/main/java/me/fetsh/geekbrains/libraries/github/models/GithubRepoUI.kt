package me.fetsh.geekbrains.libraries.github.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubRepoUI(
    val id: Long,
    val name: String,
    val description: String? = null,
    val forks: Int
) : Parcelable