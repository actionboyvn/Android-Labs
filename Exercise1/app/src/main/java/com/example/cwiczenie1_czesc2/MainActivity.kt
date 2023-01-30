package com.example.cwiczenie1_czesc2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myListener = View.OnClickListener { view ->
            when (view.getId()) {
                R.id.button1 -> {
                    val myIntent = Intent(this, Activity2::class.java)
                    startActivity(myIntent)
                }
                R.id.button2 -> {
                    val myIntent = Intent(this, Activity3::class.java)
                    startActivity(myIntent)
                }
                R.id.button3 -> {
                    val myIntent = Intent(this, Activity4::class.java)
                    startActivity(myIntent)
                }
                R.id.button7 -> {
                    val myIntent = Intent(this, Activity2::class.java)
                    startActivity(myIntent)
                }
            }
        }
        val button1: Button = findViewById(R.id.button1)
        button1.setOnClickListener(myListener)
        val button2: Button = findViewById(R.id.button2)
        button2.setOnClickListener(myListener)
        val button3: Button = findViewById(R.id.button3)
        button3.setOnClickListener(myListener)
        val button4: Button = findViewById(R.id.button7)
        button4.setOnClickListener(myListener)
    }
    override fun onResume() {
        super.onResume()
        val toast: Toast = Toast.makeText(this, "I'm back...", Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER,0,0)
        toast.show()
    }
}