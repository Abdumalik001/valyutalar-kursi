package com.example.currency.convertor.home

import OnItemClickListener
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.currency.R
import com.example.currency.adapters.ValyutaAdapter
import com.example.currency.convertor.ConvertorFragment
import com.example.currency.fragments.FacebookFragment
import com.example.valyutalarkursi.model.ValyutaData
import com.example.valyutalarkursi.model.ValyutaDataItem
import com.google.android.material.navigation.NavigationView
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment(R.layout.fragment_home), ICurrencyIterface.PresenterToView,
    OnItemClickListener,
    NavigationView.OnNavigationItemSelectedListener {
    var drawerLayout: DrawerLayout? = null
    var navigationView: NavigationView? = null
    var menu: ImageView? = null
    var calendar: ImageView? = null
    var adapter: ValyutaAdapter? = null
    var presenter: CurrencyPresenter? = null
    var recyclerView: RecyclerView? = null
    var progressBar: ProgressBar? = null
    var datee: String? = null
    var dateTxt: TextView? = null
    val list= ArrayList<ValyutaData>()


    val date = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    val month = Calendar.getInstance().get(Calendar.MONTH)
    val year = Calendar.getInstance().get(Calendar.YEAR)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadViews(view)
        datee = "$year-${month + 1}-$date"


        menu!!.setOnClickListener {
            drawerLayout!!.openDrawer(GravityCompat.START)
        }

        navigationView!!.setNavigationItemSelectedListener(this)

        presenter = CurrencyPresenter(this)
        // presenter?.loadAllData()
        presenter?.loadAllDataToDate(datee!!)
         Log.d("DDDDDD", "onViewCreated:$list")


        adapter = ValyutaAdapter(context!!)
        recyclerView!!.adapter = adapter

        calendar!!.setOnClickListener {
            val dpd = DatePickerDialog(
                context!!,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    this.datee = "" + year + "-" + (monthOfYear + 1) + "-" + dayOfMonth
                    dateTxt!!.text = datee
                    // Log.d("DDD", "onViewCreated:$datee ")
                    presenter?.loadAllDataToDate(datee!!)

                },
                year,
                month,
                date
            )

            dpd.show()
        }


    }

    private fun makeToast(s: String) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
    }

    private fun loadViews(view: View) {
        progressBar = view.findViewById(R.id.progres_bar_home)
        recyclerView = view.findViewById(R.id.rv_home)
        navigationView = view.findViewById(R.id.nav_view_home)
        drawerLayout = view.findViewById(R.id.drawer)
        menu = view.findViewById(R.id.menu_home)
        calendar = view.findViewById(R.id.calendar_home)
        dateTxt = view.findViewById(R.id.date_text)


    }

    private fun loadFragment(fragment: Fragment?) {
        drawerLayout!!.closeDrawer(GravityCompat.START)
        activity!!.supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.sile_enter,
                R.anim.sile_exit,
                R.anim.sile_pop_enter,
                R.anim.slide_pop_exit
            )
            .replace(R.id.container_main, fragment!!)
            .addToBackStack(null)
            .commit()


    }


    override fun onItemCLick(data: ValyutaDataItem) {
        makeToast(data.CcyNm_UZ)
        loadFragment(ConvertorFragment().getData(data))
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.instagram -> makeToast("Instagram ))")
                //loadFragment(FacebookFragment("https://www.instagram.com/abdumaliksafoyev/"))
            R.id.dasttur_kodi -> makeToast("Dastur kodi ))")
            R.id.facebook -> makeToast("Facebook ))")
                //loadFragment(FacebookFragment("https://www.facebook.com/abdumalik.safoyev.3"))
            R.id.telegram -> loadFragment(FacebookFragment("https://t.me/safoyev_abdumalik"))
            R.id.info -> makeDialog()
            R.id.home -> loadFragment(HomeFragment())
        }
        return true
    }

    private fun makeDialog() {
        val dialog = Dialog(context!!)
        dialog.setContentView(R.layout.dialog)
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(true)
        val ok: Button = dialog.findViewById(R.id.ok_btn)
        ok.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
        drawerLayout!!.closeDrawer(GravityCompat.START)

    }

    override fun setResponsDataToDate(data: ValyutaData) {

       // Log.d("DDD", "setResponsDataToDate: $data")
        if (list.size==0){
            list.add(data)
        }
        adapter!!.data = data
    }

    override fun setResponsData(data: ValyutaData) {
        Log.d("TTT", "setResponsData: $data")
//        adapter!!.data = data
    }

    override fun setErrorMessage(error: Throwable) {

    }

    override fun showProgress() {
        progressBar!!.visibility = View.VISIBLE

    }

    override fun hideProgress() {
        progressBar!!.visibility = View.GONE
    }


}