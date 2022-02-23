package com.example.hexcolorpicker

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var color: TextView
    private lateinit var colorString: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
    }

    private fun initUI() {
        setContentView(R.layout.activity_main)

        color = findViewById(R.id.color_display)
        colorString = findViewById(R.id.hex_color)

        setUpColorLogic(findViewById(R.id.slider_red),
            findViewById(R.id.current_hex_red),
            findViewById(R.id.current_decimal_red))
        setUpColorLogic(findViewById(R.id.slider_green),
            findViewById(R.id.current_hex_green),
            findViewById(R.id.current_decimal_green))
        setUpColorLogic(findViewById(R.id.slider_blue),
            findViewById(R.id.current_hex_blue),
            findViewById(R.id.current_decimal_blue))


        color.setBackgroundColor(Color.parseColor(colorString.text.toString()))
    }

    private fun setUpColorLogic(colorSlider: SeekBar, outHex: TextView, outDec: TextView) {
        colorSlider.max = 255

        val completeColorString: TextView = findViewById(R.id.hex_color)

        colorSlider.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {

            val red: TextView = findViewById(R.id.current_hex_red)
            val green: TextView = findViewById(R.id.current_hex_green)
            val blue: TextView = findViewById(R.id.current_hex_blue)

            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

                val hexStr = "#${formatString(red)}${formatString(green)}${formatString(blue)}"

                outHex.text = "#" + Integer.toHexString(colorSlider.progress)
                outDec.text = colorSlider.progress.toString()
                completeColorString.text = hexStr

                color.setBackgroundColor(Color.parseColor(hexStr))
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                val hexStr = "#${formatString(red)}${formatString(green)}${formatString(blue)}"

                outHex.text = "#" + Integer.toHexString(colorSlider.progress)
                outDec.text = colorSlider.progress.toString()
                completeColorString.text = hexStr

                color.setBackgroundColor(Color.parseColor(hexStr))
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                val hexStr = "#${formatString(red)}${formatString(green)}${formatString(blue)}"

                outHex.text = "#" + Integer.toHexString(colorSlider.progress)
                outDec.text = colorSlider.progress.toString()
                completeColorString.text = hexStr

                color.setBackgroundColor(Color.parseColor(hexStr))
            }
        })
    }

    private fun formatString(view: TextView): String {
        var string: String = view.text.toString().drop(1)
        if (string.toCharArray().size < 2) {
            string = "0${string}"
        }
        return string
    }
}