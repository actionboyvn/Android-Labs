package com.example.cwiczenie1_czesc2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Activity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)

        val myListener = View.OnClickListener { view ->
            when (view.getId()) {
                R.id.button4 -> {
                    var message = ""
                    val anyText: EditText = findViewById(R.id.anyText)
                    val emailText: EditText = findViewById(R.id.emailText)
                    val decimalNumber: EditText = findViewById(R.id.decimalNumber)
                    val phoneNumber: EditText = findViewById(R.id.phoneNumber)
                    message += "tekst: " + anyText.getText() +"\n"
                    message += "e-mail: " + emailText.getText() +"\n"
                    message += "liczba: " + decimalNumber.getText() +"\n"
                    message += "tel. : " + phoneNumber.getText()
                    val toast: Toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.CENTER,0,0)
                    toast.show()
                }
            }
        }
        val toast: Button = findViewById(R.id.button4)
        toast.setOnClickListener(myListener)
    }
    fun finishActivity3(view: View?) {
        finish()
    }
}