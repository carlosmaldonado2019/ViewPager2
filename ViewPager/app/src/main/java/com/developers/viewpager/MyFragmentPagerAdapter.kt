package com.developers.viewpager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.View

/**
 * Created by Amanjeet Singh on 16/11/17.
 */
class MyFragmentPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {



    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            return Page1()
        } else if (position ==1){
            return Page2()
        }else{
            return Page3()
        }
    }

    override fun getCount(): Int {
        return 3
    }

}