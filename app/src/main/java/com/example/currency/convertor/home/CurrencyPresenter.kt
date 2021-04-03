package com.example.currency.convertor.home

import android.util.Log
import com.example.currency.network.CBUInterface
import com.example.currency.network.NetworConnection
import com.example.valyutalarkursi.model.ValyutaData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrencyPresenter(var view: ICurrencyIterface.PresenterToView) :
    ICurrencyIterface.ViewToPresenter {

    private var cbuInterface: CBUInterface? = null

    init {
        cbuInterface = NetworConnection.getRetrofit().create(CBUInterface::class.java)
    }

    override fun loadAllData() {
        view.showProgress()
        cbuInterface?.let {
            it.getAllData().enqueue(object : Callback<ValyutaData> {
                override fun onResponse(call: Call<ValyutaData>, response: Response<ValyutaData>) {
                    var list: ValyutaData? = null
                    if (response.isSuccessful) {
                        list = response.body()
                        //    Log.d("OOOO", "onResponse: $list")
                        view.setResponsData(list!!)
                        view.hideProgress()
                    }
                }

                override fun onFailure(call: Call<ValyutaData>, t: Throwable) {
                    view.setErrorMessage(t)
                }

            })

        }
    }

    override fun loadAllDataToDate(date: String) {
        view.showProgress()
        cbuInterface?.let {
            it.getAllDataToDate(date).enqueue(object : Callback<ValyutaData> {
                override fun onResponse(call: Call<ValyutaData>, response: Response<ValyutaData>) {
                    if (response.isSuccessful && response.body()!!.size > 0) {
                        view.setResponsDataToDate(response.body()!!)
                        view.hideProgress()
                    }
                }

                override fun onFailure(call: Call<ValyutaData>, t: Throwable) {
                    view.hideProgress()
                    view.setErrorMessage(t)
                }

            })
        }
    }

    override fun loadAllDataOneItem() {
    }

}