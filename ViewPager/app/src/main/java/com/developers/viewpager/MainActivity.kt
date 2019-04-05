package com.developers.viewpager

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.VideoView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_page1.*
import kotlinx.android.synthetic.main.fragment_page3.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    lateinit var adapter: MyFragmentPagerAdapter

    // Represent whether the lastly pressed key is numeric or not
    var lastNumeric: Boolean = false
    // Represent that current state is in error or not
    var stateError: Boolean = false
    // If true, do not allow to add another DOT
    var lastDot: Boolean = false
    lateinit var video: VideoView


    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = MyFragmentPagerAdapter(supportFragmentManager)
        view_pager.adapter = adapter


        view_pager.setPageTransformer(false) { page: View, position: Float ->
            if (position < -1) {    // [-Infinity,-1)
                // This page is way off-screen to the left.
                page.setAlpha(0f)

            } else if (position <= 0) {    // [-1,0]
                page.setAlpha(1f)
                page.setPivotX(page.getWidth().toFloat())
                page.setRotationY(-90 * Math.abs(position))

            } else if (position <= 1) {    // (0,1]
                page.setAlpha(1f)
                page.setPivotX(0f)
                page.setRotationY(90 * Math.abs(position))

            } else {    // (1,+Infinity]
                // This page is way off-screen to the right.
                page.setAlpha(0f)

            }
        }
    }

    fun onDigit(view: View) {
        if (stateError) {
            // If current state is Error, replace the error message
            txtInput.text = (view as Button).text
            stateError = false
        } else {
            // If not, already there is a valid expression so append to it
            txtInput.append((view as Button).text)
        }
        // Set the flag
        lastNumeric = true
    }

    /**
     * Append . to the TextView
     */
    fun onDecimalPoint(view: View) {
        if (lastNumeric && !stateError && !lastDot) {
            txtInput.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    /**
     * Append +,-,*,/ operators to the TextView
     */
    fun onOperator(view: View) {
        if (lastNumeric && !stateError) {
            txtInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false    // Reset the DOT flag
        }
    }

    /**
     * Clear the TextView
     */
    fun onClear(view: View) {
        this.txtInput.text = ""
        lastNumeric = false
        stateError = false
        lastDot = false
    }

    /**
     * Calculate the output using Exp4j
     */
    fun onEqual(view: View) {
        // If the current state is error, nothing to do.
        // If the last input is a number only, solution can be found.
        if (lastNumeric && !stateError) {
            // Read the expression
            val txt = txtInput.text.toString()
            // Create an Expression (A class from exp4j library)
            val expression = ExpressionBuilder(txt).build()
            try {
                // Calculate the result and display
                val result = expression.evaluate()
                txtInput.text = result.toString()
                lastDot = true // Result contains a dot
            } catch (ex: ArithmeticException) {
                // Display an error message
                txtInput.text = "Error"
                stateError = true
                lastNumeric = false
            }
        }
    }
}
