package com.example.cwiczenie4

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.SubMenu
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private var main_menu: Menu? = null
    private var bg_style = 0
    private var theme_num = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data: SharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        theme_num = data.getInt("theme_num", 0)
        bg_style = data.getInt("bg_style", 0)
        when (theme_num) {
            0 -> {
                setTheme(R.style.Theme_Cwiczenie4)
            }
            1 -> {
                setTheme(R.style.theme1)
            }
            2 -> {
                setTheme(R.style.theme2)
            }
            3 -> {
                setTheme(R.style.theme_custom)
            }
        }
        when (bg_style) {
            0 -> {
                theme.applyStyle(R.style.Theme_Cwiczenie4, true)
            }
            1 -> {
                theme.applyStyle(R.style.theme1, true)
            }
            2 -> {
                theme.applyStyle(R.style.theme2, true)
            }
            3 -> {
                theme.applyStyle(R.style.theme_custom, true)
            }
        }
        setContentView(R.layout.activity_main)

        val navHostFragment=supportFragmentManager.findFragmentById(R.id.navContainer)
                as NavHostFragment
        val navController = navHostFragment.navController
        val bnNavView = findViewById<View>(R.id.bottom_nav) as BottomNavigationView
        val appBarConfig : AppBarConfiguration =
            AppBarConfiguration(setOf(R.id.left_frag,R.id.center_frag,R.id.right_frag))
        setupActionBarWithNavController(navController,appBarConfig)
        bnNavView.setupWithNavController(navController)

        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fab.setOnClickListener { view ->
            main_menu?.add(0, 0, 0, "New Option")
            val mySB = Snackbar.make(view, "FAB action triggered",
                Snackbar.LENGTH_SHORT)
            mySB.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        main_menu = menu
        menuInflater.inflate(R.menu.main_menu, menu)

        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.theme_default -> {
                this.setTheme(R.style.Theme_Cwiczenie4)
                theme.applyStyle(R.style.Theme_Cwiczenie4, true)
                this.theme_num = 0
                this.bg_style = 0
            }
            R.id.theme_1 -> {
                this.setTheme(R.style.theme1)
                theme.applyStyle(R.style.theme1, true)
                this.theme_num = 1
                this.bg_style = 1
            }
            R.id.theme_2 -> {
                this.setTheme(R.style.theme2)
                theme.applyStyle(R.style.theme2, true)
                this.theme_num = 2
                this.bg_style = 2
            }
            R.id.theme_custom -> {
                this.setTheme(R.style.theme_custom)
                theme.applyStyle(R.style.theme_custom, true)
                this.theme_num = 3
                this.bg_style = 3
            }
            else -> {
                super.onOptionsItemSelected(item)
                return true
            }
        }
        val data = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        var editor = data.edit()
        editor.putInt("theme_num", theme_num)
        editor.putInt("bg_style", bg_style)
        editor.commit()
        super.onOptionsItemSelected(item)
        recreate()
        return false
    }


}