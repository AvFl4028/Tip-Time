package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnCal: Button = binding.calculate
        btnCal.setOnClickListener{ calculateTip() }
    }

    private fun calculateTip(){
        val stringCost:String = binding.costView.text.toString()
        val cost = stringCost.toDoubleOrNull()
        val roundUp:Boolean = binding.roundUp.isChecked

        val tipPercentage:Double = when(binding.btnGroup.checkedRadioButtonId){
            R.id.amazing_service -> .20
            R.id.good_service -> .18
            else -> .15
        }


        if(cost == null || cost == 0.0){
            displayTip(0.0)
            return
        }

        var tip = tipPercentage * cost

        if (roundUp){
            tip = kotlin.math.ceil(tip)
        }

        NumberFormat.getCurrencyInstance()
        displayTip(tip)
    }

    private fun displayTip(i:Double){
        val formattedTip = NumberFormat.getCurrencyInstance().format(i)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)

    }

}