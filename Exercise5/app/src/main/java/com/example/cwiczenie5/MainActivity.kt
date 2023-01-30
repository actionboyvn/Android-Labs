package com.example.cwiczenie5


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment=supportFragmentManager.findFragmentById(R.id.navContainer)
                as NavHostFragment
        val navController = navHostFragment.navController
        val bnNavView = findViewById<View>(R.id.bottom_nav) as BottomNavigationView
        val appBarConfig : AppBarConfiguration =
            AppBarConfiguration(setOf(R.id.primary_frag,R.id.list1_frag,R.id.list2_frag,R.id.swipe_frag))
        setupActionBarWithNavController(navController,appBarConfig)
        bnNavView.setupWithNavController(navController)

    }
}