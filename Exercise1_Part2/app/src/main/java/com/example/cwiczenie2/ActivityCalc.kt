package com.example.cwiczenie2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ActivityCalc : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc)
        val iii: Intent = getIntent()
        val bundle = iii.extras
        var val1:Int? = bundle?.getInt("val1",1)
        var val2:Int? = bundle?.getInt("val2",1)
        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        val buttonSubtract = findViewById<Button>(R.id.buttonSubtract)
        val buttonMultiply = findViewById<Button>(R.id.buttonMultiply)
        buttonAdd.setOnClickListener { _ ->
            val res = val2?.let { val1?.plus(it) }
            iii.putExtra("result",res)
            setResult(RESULT_OK, iii)
            finish()
        }
        buttonSubtract.setOnClickListener { _ ->
            val res = val1?.minus(val2!!)
            iii.putExtra("result",res)
            setResult(RESULT_OK, iii)
            finish()
        }
        buttonMultiply.setOnClickListener { _ ->
            val res = val2?.times(val1!!)
            iii.putExtra("result",res)
            setResult(RESULT_OK, iii)
            finish()
        }
    }
}