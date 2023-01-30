package com.example.cwiczenie2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts

class ActivityRight : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_right)
        val buttonCalc = findViewById<Button>(R.id.buttonCalc)
        val et1:EditText = findViewById<EditText>(R.id.eText1)
        val et2 = findViewById<EditText>(R.id.eText2)

        val startForResult =
            registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) { result: ActivityResult ->
                if (result.resultCode == RESULT_OK)
                {
                    val intent = result.data
                    val i = intent?.getIntExtra("result",0)
                    if (i != null) {
                        val str = i.toString()
                        val tv1: TextView = findViewById(R.id.result)
                        tv1.text = str
                    }
                }
            }
        buttonCalc.setOnClickListener { _ ->
            val val1:Int = et1.text.toString().toInt()
            val val2:Int = et2.text.toString().toInt()
            if (et1.text.toString() != "" && et2.text.toString() != "") {
                val bundle = Bundle()
                bundle.putInt("val1", val1)
                bundle.putInt("val2", val2)
                val intent = Intent(this, ActivityCalc::class.java)
                intent.putExtras(bundle)
                startForResult.launch(intent)
            }
        }
    }
}