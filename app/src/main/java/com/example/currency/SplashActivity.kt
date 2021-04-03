package com.example.currency

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import com.example.currency.fragments.ConnectionErrorFragment
import com.example.currency.utils.NetworkOn

class SplashActivity : AppCompatActivity() {
    var handler = Handler()

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if (NetworkOn(this)) {
            handler.postDelayed({
                startActivity(Intent(SplashActivity::class.java, MainActivity::class.java))
                finish()
            }, 1000)

        } else {
            loadFragment(ConnectionErrorFragment())
        }


    }

    private fun Intent(java: Class<SplashActivity>, java1: Class<MainActivity>): Intent {
        val intent = Intent(this, MainActivity::class.java)
        return intent
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.container_splash, fragment)
            .addToBackStack(null)
            .commit()

    }
}