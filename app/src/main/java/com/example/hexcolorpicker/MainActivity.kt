package com.example.hexcolorpicker

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity(), NumberChangedListener {

    private lateinit var color: TextView
    private lateinit var colorString: TextView

    private lateinit var redHex: TextView
    private lateinit var greenHex: TextView
    private lateinit var blueHex: TextView

    private lateinit var sliderRed: SeekBar
    private lateinit var sliderGreen: SeekBar
    private lateinit var sliderBlue: SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
    }

    @SuppressLint("SetTextI18n")
    private fun initUI() {
        setContentView(R.layout.activity_main)

        color = findViewById(R.id.color_display)
        colorString = findViewById(R.id.hex_color)

        redHex = findViewById(R.id.current_hex_red)
        greenHex = findViewById(R.id.current_hex_green)
        blueHex = findViewById(R.id.current_hex_blue)

        sliderRed = findViewById(R.id.slider_red)
        sliderGreen = findViewById(R.id.slider_green)
        sliderBlue = findViewById(R.id.slider_blue)

        val compute: ImageButton = findViewById(R.id.compute)
        val inputHex: EditText = findViewById(R.id.hex_input)
        compute.setOnClickListener {
            if (inputHex.text.toString().length == 6){
                val hexArray = inputHex.text.toString().chunked(2)
                redHex.text = "#${formatTwoDigits(hexArray[0])}"
                greenHex.text = "#${formatTwoDigits(hexArray[1])}"
                blueHex.text = "#${formatTwoDigits(hexArray[2])}"

                sliderRed.progress = Integer.decode("0x${hexArray[0]}")
                sliderBlue.progress = Integer.decode("0x${hexArray[1]}")
                sliderGreen.progress = Integer.decode("0x${hexArray[2]}")

                inputHex.text.clear()
                colorString.text = "#${formatString(redHex)}${formatString(greenHex)}${formatString(blueHex)}"
                try {

                } catch (e: NumberFormatException) {
                    Toast
                        .makeText(this,
                        "Only 6 characters are allowed. Characters must be numbers or letters from a to f.",
                        Toast.LENGTH_SHORT)
                        .show()
                    inputHex.text.clear()
                }
            }
        }

        setUpColorLogic(sliderRed, redHex)
        setUpColorLogic(sliderGreen, greenHex)
        setUpColorLogic(sliderBlue, blueHex)


        color.setBackgroundColor(Color.parseColor(colorString.text.toString()))
    }

    private fun setUpColorLogic(colorSlider: SeekBar, outHex: TextView) {
        colorSlider.max = 255
        colorSlider.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                setResultString(outHex, colorSlider)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                setResultString(outHex, colorSlider)
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                setResultString(outHex, colorSlider)
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun setResultString(outHex: TextView, colorSlider: SeekBar) {
        colorString.text
        val hexStr = "#${formatString(redHex)}${formatString(greenHex)}${formatString(blueHex)}"

        outHex.text = "#" + formatTwoDigits(Integer.toHexString(colorSlider.progress))
        colorString.text = hexStr

        color.setBackgroundColor(Color.parseColor(hexStr))
    }

    private fun formatString(view: TextView): String {
        return formatTwoDigits(view.text.toString().drop(1))
    }

    private fun formatTwoDigits(string: String): String {
        if (string.toCharArray().size < 2) {
            return "0${string}"
        }
        return string
    }

    override fun onNumberChanged(number: String) {

    }
}

interface NumberChangedListener {
    fun onNumberChanged(number: String)
}