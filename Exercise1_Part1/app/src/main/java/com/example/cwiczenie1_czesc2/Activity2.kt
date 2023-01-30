package com.example.cwiczenie1_czesc2

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*

class Activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        val text: TextView = findViewById(R.id.textView2)
        val toggle: ToggleButton = findViewById(R.id.toggleButton)
        toggle.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked)
                    text.setText("Text 2")
                else
                    text.setText("Text 1")
            }
        val myListener = View.OnClickListener { view ->
            when (view.getId()) {
                R.id.button -> {
                    var message = ""
                    val radioButton1: RadioButton = findViewById(R.id.radioButton1)
                    val radioButton2: RadioButton = findViewById(R.id.radioButton2)
                    if (radioButton1.isChecked)
                        message += "The image is visible\n"
                    else
                        if (radioButton2.isChecked)
                            message += "The image is invisible\n"
                        else
                            message += "None of the radio buttons is being selected\n"
                    if (toggle.isChecked)
                        message += "Toggle button is on"
                    else
                        message += "Toggle button is off"
                    val toast: Toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.CENTER,0,0)
                    toast.show()
                }
                R.id.checkBox -> {
                    val checkBox: CheckBox = findViewById(R.id.checkBox);
                    if (checkBox.isChecked()) {
                        //checkBox.setChecked(false);
                        text.setTypeface(null, Typeface.BOLD)
                    } else {
                        text.setTypeface(null, Typeface.NORMAL)
                    }
                }
            }
        }
        val toast: Button = findViewById(R.id.button)
        toast.setOnClickListener(myListener)

        val checkBox: CheckBox = findViewById(R.id.checkBox);
        checkBox.setOnClickListener(myListener)

    }
    fun onRadioButtonClicked(view: View?) {
        val image: ImageView = findViewById(R.id.imageView)
        if (view is RadioButton) {
            val checked = view.isChecked
            when (view.getId()) {
                R.id.radioButton1 ->
                    if (checked) {
                        image.setVisibility(View.VISIBLE)
                        getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                    }
                R.id.radioButton2 ->
                    if (checked) {
                        image.setVisibility(View.INVISIBLE)
                        getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                    }
            }

        }
    }
    fun finishActivity2(view: View?) {
        finish()
    }
}
