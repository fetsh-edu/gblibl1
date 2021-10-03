package me.fetsh.geekbrains.libraries.github.models

import java.math.BigInteger
import java.security.MessageDigest

interface RepoInterpolator {
    fun interpolate(repo: GithubRepoUI) : BigInteger
}

class FancyRepoInterpolator : RepoInterpolator {
    override fun interpolate(repo: GithubRepoUI) : BigInteger {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(repo.name.toByteArray(Charsets.UTF_8)))
    }
}