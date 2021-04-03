package com.example.currency.network

import com.example.valyutalarkursi.model.ValyutaData
import com.example.valyutalarkursi.model.ValyutaDataItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CBUInterface {

    @GET("arkhiv-kursov-valyut/json/")
    fun getAllData(): Call<ValyutaData>

    @GET("arkhiv-kursov-valyut/json/all/{date}/ ")
    fun getAllDataToDate(@Path("date") date: String): Call<ValyutaData>

    @GET("arkhiv-kursov-valyut/json/{type}/{date}/")
    fun getItemData(@Path("type") type: String, @Path("date") date: String): Call<ValyutaData>

}