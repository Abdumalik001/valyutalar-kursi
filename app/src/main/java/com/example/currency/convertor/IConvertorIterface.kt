package com.example.currency.convertor

import com.example.valyutalarkursi.model.ValyutaDataItem
import java.util.ArrayList

interface IConvertorIterface {

    interface ConvertViewToPresenter {
        fun replaceData(currentVal: String, resultVal: String)
        fun getItemDataToDate(type: String, date: String)

    }

    interface ConvertPresenterToView {
        fun sendReplaceData(currentVal: String, resultVal: String)
        fun sendItemDataToDate(data: ValyutaDataItem)
        fun sendError(e: String)
        fun showProgress()
        fun hideProgress()

    }
}


// API dan malumot o`qib olishda responsda faqat 1 tadan  malumot keladi, shu malumotni listviewga realtime da chizib borish qanday qilinadi, ozroq yo`nalish berib yuboring