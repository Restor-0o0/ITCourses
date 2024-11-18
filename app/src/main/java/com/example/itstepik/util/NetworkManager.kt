package com.example.itstepik.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object NetworkManager {
    fun isOnline(context: Context): Boolean{
        var result = false
        val connectivituManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        connectivituManager?.run{
            connectivituManager.getNetworkCapabilities(connectivituManager.activeNetwork)?.run{
                result = when{
                    hasTransport(NetworkCapabilities.TRANSPORT_VPN) ->true
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    else -> false
                }
            }
        }
        return result
    }
}