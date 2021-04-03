package com.example.currency.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.currency.MainActivity
import com.example.currency.R
import com.example.currency.SplashActivity
import com.example.currency.utils.NetworkOn

class ConnectionErrorFragment : Fragment(R.layout.fragment_error) {
    var repeatB: Button? = null
    var exitB: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repeatB = view.findViewById(R.id.repeat_connection)
        exitB = view.findViewById(R.id.exit)

        repeatB!!.setOnClickListener {
            if (NetworkOn(context!!)){

                startActivity(Intent(SplashActivity::class.java, MainActivity::class.java))
                activity!!.supportFragmentManager.beginTransaction().remove(this).commit()
                activity!!.finish()
            }
            if (!NetworkOn(context!!)){
                activity!!.supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.anim.sile_enter,R.anim.sile_exit,R.anim.sile_pop_enter,R.anim.slide_pop_exit)
                    .replace(R.id.container_splash,ConnectionErrorFragment())
                    .commit()
            }

        }

        exitB!!.setOnClickListener {
            activity!!.finish()
        }

    }

    private fun Intent(java: Class<SplashActivity>, java1: Class<MainActivity>): Intent? {
        val intent=Intent(context,MainActivity::class.java)
        return intent
    }


}