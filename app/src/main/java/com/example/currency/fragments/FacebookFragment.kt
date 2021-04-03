package com.example.currency.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.example.currency.R

class FacebookFragment(var url:String) : Fragment(
    R.layout.facebook_fragment
) {

    var myWebView: WebView ?=null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)

    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       myWebView = view.findViewById(R.id.webView)
        myWebView!!.loadUrl(url)
        myWebView!!.settings.javaScriptEnabled = true



    }

}