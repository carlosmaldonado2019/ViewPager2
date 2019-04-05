package com.developers.viewpager


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


/**
 * A simple [Fragment] subclass.
 */
class Page1 : Fragment() {


    lateinit var txtInput: TextView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v:View= inflater!!.inflate(R.layout.fragment_page1, container, false)
        v.tag = 1
        txtInput = v.findViewById(R.id.txtInput)
        return v
    }

}// Required empty public constructor
