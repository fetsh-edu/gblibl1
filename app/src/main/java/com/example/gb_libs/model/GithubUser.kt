package com.example.gb_libs.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    val id: String,
    val login: String
) : Parcelable