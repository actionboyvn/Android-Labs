package com.example.cwiczenie1_czesc2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Activity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_4)
    }
    fun finishActivity4(view: View?) {
        finish()
    }
}