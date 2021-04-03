package com.example.currency

import OnItemClickListener
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.currency.convertor.ConvertorFragment
import com.example.currency.convertor.home.HomeFragment
import com.example.valyutalarkursi.model.ValyutaDataItem


class MainActivity : AppCompatActivity(), OnItemClickListener {



    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        if (savedInstanceState == null) {
            addFragment(HomeFragment())
        }
    }

    override fun onItemCLick(data: ValyutaDataItem) {
        addFragment(ConvertorFragment().getData(data))
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.sile_enter,
                R.anim.sile_exit,
                R.anim.sile_pop_enter,
                R.anim.slide_pop_exit
            )
            .replace(R.id.container_main, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else super.onBackPressed()
    }
}





