package com.example.currency.utils

import android.content.Context
import android.net.ConnectivityManager

const val BASE_URL="https://cbu.uz/uz/"
const val KEY="https://cbu.uz/uz/"




 fun NetworkOn(context: Context): Boolean {
//            val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//            val isMetered = cm.isActiveNetworkMetered
//            return isMetered
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    val netInfo = cm!!.activeNetworkInfo
    return netInfo != null && netInfo.isConnectedOrConnecting
}