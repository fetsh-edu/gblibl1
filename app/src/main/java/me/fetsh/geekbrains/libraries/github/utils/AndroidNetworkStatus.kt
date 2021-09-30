package me.fetsh.geekbrains.libraries.github.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject

interface NetworkStatus{
    fun isOnline(): Observable<Boolean>
    fun isOnlineSingle(): Single<Boolean>
}

class AndroidNetworkStatus(context: Context) : NetworkStatus {
    private val statusSubject: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)

    init {
        statusSubject.onNext(false)
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val request = NetworkRequest.Builder().build()
        connectivityManager.registerNetworkCallback(request, object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                statusSubject.onNext(true)
            }

            override fun onUnavailable() {
                statusSubject.onNext(false)
            }

            override fun onLost(network: Network) {
                statusSubject.onNext(false)
            }
        })
    }

    override fun isOnline(): Subject<Boolean> = statusSubject
    override fun isOnlineSingle(): Single<Boolean> = statusSubject.first(false)
}