package com.example.currency.convertor

import android.util.Log
import com.example.currency.network.CBUInterface
import com.example.currency.network.NetworConnection
import com.example.valyutalarkursi.model.ValyutaData
import com.example.valyutalarkursi.model.ValyutaDataItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConvertorPresenter(var view: IConvertorIterface.ConvertPresenterToView) :
    IConvertorIterface.ConvertViewToPresenter {

    var cbuInterface: CBUInterface? = null

    init {
        cbuInterface = NetworConnection.getRetrofit().create(CBUInterface::class.java)
    }

    override fun replaceData(currentVal: String, resultVal: String) {
        view.sendReplaceData(resultVal, currentVal)
    }

    override fun getItemDataToDate(type: String, date: String) {
        view.showProgress()
        cbuInterface?.getItemData(type, date)!!.enqueue(object : Callback<ValyutaData> {
            override fun onResponse(
                call: Call<ValyutaData>,
                response: Response<ValyutaData>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    //   Log.d("RRRRR", "onResponse: ${response.body()!![0].Date}")
                    view.sendItemDataToDate(response.body()!![0])
                    view.hideProgress()
                } else {
                    view.hideProgress()
                    view.sendError("SERVERDA XATOLIK")
                }

            }


            override fun onFailure(call: Call<ValyutaData>, t: Throwable) {
                view.hideProgress()
                view.sendError(t.message.toString())
            }

        })
    }
}