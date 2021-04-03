package com.example.currency.convertor.home

import com.example.valyutalarkursi.model.ValyutaData

interface ICurrencyIterface {
    interface ViewToPresenter {
        fun loadAllData()
        fun loadAllDataToDate(date:String)
        fun loadAllDataOneItem()

    }

    interface PresenterToView {
        fun setResponsDataToDate(data:ValyutaData)
        fun setResponsData(data: ValyutaData)
        fun setErrorMessage(error: Throwable)
        fun showProgress()
        fun hideProgress()

    }


}