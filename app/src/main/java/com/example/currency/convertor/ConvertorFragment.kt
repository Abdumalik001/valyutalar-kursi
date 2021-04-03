package com.example.currency.convertor

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.currency.R
import com.example.currency.adapters.ConvertAdapter
import com.example.currency.convertor.watcher.Iwatcher
import com.example.currency.convertor.watcher.SimpleTextWatcher
import com.example.currency.utils.KEY
import com.example.valyutalarkursi.model.ValyutaDataItem
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class ConvertorFragment : Fragment(R.layout.fragment_convertor),
    IConvertorIterface.ConvertPresenterToView, Iwatcher {
    var text: String? = null
    var currentValyuta: TextView? = null
    var resultValyuta: TextView? = null
    val convertorPresenter = ConvertorPresenter(this)
    var replaceButton: ImageButton? = null
    var isReplase = false
    var convert: EditText? = null
    var textWatcher = SimpleTextWatcher(this)
    var rate: Double? = null
    var convertedData: TextView? = null
    var type: String? = null
    var date: String? = null
    val adapter = ConvertAdapter()
    var rv: RecyclerView? = null
    var progressBar: ProgressBar? = null
    var anim = true
    var back:ImageView?=null



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle: Bundle = arguments!!
        bundle.let {
            text = it.getString(KEY)
            rate = it.getDouble("rate")
            type = it.getString("type")
            date = it.getString("date")
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = view.findViewById(R.id.progres_bar)
        convert = view.findViewById(R.id.convert)
        rv = view.findViewById(R.id.rv_converter)
        convertedData = view.findViewById(R.id.converted_data)
        replaceButton = view.findViewById(R.id.replace_data)
        resultValyuta = view.findViewById(R.id.result_data)
        currentValyuta = view.findViewById(R.id.current_data)
        back=view.findViewById(R.id.exit_convertor)


        currentValyuta!!.text = text
        convertedData!!.text = rate!!.toString()

        convert?.addTextChangedListener(textWatcher)

        for (i in 0..100) {
            convertorPresenter.getItemDataToDate(type!!, incDate(date!!)[i])
        }

        rv!!.adapter = adapter

        replaceButton!!.setOnClickListener {
            val animation: Animation? = AnimationUtils.loadAnimation(context, R.anim.rotate)
            replaceButton!!.startAnimation(animation)
            convertorPresenter.replaceData(
                currentValyuta!!.text.toString(),
                resultValyuta!!.text.toString()
            )
            isReplase = !isReplase
            calcVal(convert!!.text.toString())

        }


        back!!.setOnClickListener {
            activity!!.onBackPressed()
        }
    }

    private fun formatDate(date: String): String {
        val a = date.split(".").toTypedArray()
        var b = ""
        b = a[0]
        a[0] = a[2]
        a[2] = b

        val builder = StringBuilder()
        builder.append(a[0])
        builder.append("-")
        builder.append(a[1])
        builder.append("-")
        builder.append(a[2])
        return builder.toString()
    }

    @SuppressLint("SimpleDateFormat")
    private fun incDate(date: String): ArrayList<String> {
        var d = formatDate(date)
        val list = ArrayList<String>()
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val calendar = Calendar.getInstance()
        for (i in 0..100) {
            calendar.time = sdf.parse(d)
            calendar.add(Calendar.DATE, -7)
            d = sdf.format(calendar.time)
            list.add(d)
        }
        return list
    }


    fun calcVal(s: String) {
        if (!s.isEmpty()) {
            if (!isReplase) {
                convertedData!!.text = String.format("%.2f", (s.toDouble() * rate!!))
            } else {
                convertedData!!.text = String.format("%.2f", (s.toDouble() / rate!!))
            }

        } else {
            convertedData!!.text = "0"
        }
    }


    fun getData(data: ValyutaDataItem): ConvertorFragment {
        val fragment = ConvertorFragment()
        val bundle = Bundle()
        bundle.putString(KEY, data.CcyNm_UZ)
        bundle.putDouble("rate", data.Rate.toDouble())
        bundle.putString("type", data.Ccy)
        bundle.putString("date", data.Date)
        fragment.arguments = bundle
        return fragment
    }


    override fun sendReplaceData(currentVal: String, resultVal: String) {
        currentValyuta!!.text = currentVal
        resultValyuta!!.text = resultVal
    }


    override fun sendItemDataToDate(data: ValyutaDataItem) {
        val list = ArrayList<ValyutaDataItem>()
        list.add(data)
        adapter.listData = list
        Log.d("AAAA", "sendItemDataToDate: $data")

    }

    override fun sendError(e: String) {
        progressBar!!.visibility = View.GONE
    }

    override fun showProgress() {
        progressBar!!.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar!!.visibility = View.GONE

    }

    override fun onTextChanged(text: String) {
        calcVal(text)
    }

}