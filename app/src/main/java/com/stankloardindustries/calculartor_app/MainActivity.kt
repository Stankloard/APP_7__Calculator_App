package com.stankloardindustries.calculartor_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*;
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //getActionBar()?.hide()
    }

    var lastNumeric: Boolean = false
    var lastDot:Boolean = false

    fun onDigit(view: View){
        TextView1.append((view as Button).text)
        lastNumeric = true
    }

    fun onClear(view: View){
        TextView1.text = ""
        lastNumeric = false
        lastDot = false
    }

    fun onEqual(view: View){
        if(lastNumeric){
            var tvValue = TextView1.text.toString()
            var prefix = ""

            try{
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                if(tvValue.contains("-")){
                    val splitNum = tvValue.split("-")
                    var one = splitNum[0]
                    var two = splitNum[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    TextView1.text = removeZero((one.toDouble() - two.toDouble()).toString())
                }
                if(tvValue.contains("+")){
                    val splitNum = tvValue.split("+")
                    var one = splitNum[0]
                    var two = splitNum[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    TextView1.text = removeZero((one.toDouble() + two.toDouble()).toString())
                }
                if(tvValue.contains("*")){
                    val splitNum = tvValue.split("*")
                    var one = splitNum[0]
                    var two = splitNum[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    TextView1.text = removeZero((one.toDouble() * two.toDouble()).toString())
                }
                if(tvValue.contains("/")){
                    val splitNum = tvValue.split("/")
                    var one = splitNum[0]
                    var two = splitNum[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    TextView1.text = removeZero((one.toDouble() / two.toDouble()).toString())
                }
            } catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    fun onOperator(view: View){
        if(lastNumeric && !isOperatorAdded(TextView1.text.toString())){
            TextView1.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    fun onDecimalPoint(view: View){
        if(lastNumeric && !lastDot){
            TextView1.append(".")
            lastNumeric = false
            lastDot = false
        }
    }

    private fun removeZero(result: String): String {
        var value = result
        if(result.endsWith(".0")){
            value = result.substring(0, result.length - 2)
        }
        return value
    }

    private fun isOperatorAdded(value: String): Boolean{
        return if(value.startsWith("-")){
            false
        } else{
            value.contains("/") || value.contains("-") || value.contains("+") || value.contains("*")
        }
    }
}