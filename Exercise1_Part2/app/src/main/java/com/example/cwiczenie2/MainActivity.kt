package com.example.cwiczenie2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val result2 = findViewById<TextView>(R.id.result2)
        val startForResult =
            registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) { result: ActivityResult ->
                if (result.resultCode == RESULT_OK)
                {
                    val intent = result.data // the same: intent = result.getData()
                    val i = intent?.getIntExtra("result",0) //get result data
                    if (i != null) {
                        val str = i.toString()
                        //Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
                        result2.text = str;
                    }
                }
            }

        val bnNavView = findViewById<View>(R.id.bottom_nav) as BottomNavigationView
        bnNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bn_left -> startActivity(
                    Intent(this, ActivityLeft::class.java)
                )
                R.id.bn_center -> {
                    val bundle = Bundle()
                    val intent = Intent(this, ActivityCenter::class.java)
                    intent.putExtras(bundle)
                    startForResult.launch(intent)
                    /*
                    startActivity(
                        Intent(this, ActivityCenter::class.java)
                    )
                    */
                }
                R.id.bn_right -> startActivity(
                    Intent(this, ActivityRight::class.java))
            }
            true
        }

    }
}