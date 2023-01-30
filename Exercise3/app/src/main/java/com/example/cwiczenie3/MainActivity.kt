package com.example.cwiczenie3

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

//class MainActivity : AppCompatActivity(),  StaticFragment.OnSelectListener{
class MainActivity : AppCompatActivity(){
    /*
    var frag1: Fragment1? = null
    var frag2: Fragment2? = null
    var myTrans: FragmentTransaction? = null
    private val TAG_F1 = "Fragment1"
    private val TAG_F2 = "Fragment2"
    */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val navHostFragment=supportFragmentManager.findFragmentById(R.id.navContainer)
                as NavHostFragment
        val navController = navHostFragment.navController
        val bnNavView = findViewById<View>(R.id.bottom_nav) as BottomNavigationView
        /*
        bnNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.left_frag -> navController.navigate(R.id.action_to_left_frag)
                R.id.center_frag -> navController.navigate(R.id.action_to_center_frag)
                R.id.right_frag -> navController.navigate(R.id.action_to_right_frag)
                R.id.extra_frag -> navController.navigate(R.id.action_to_extra_frag)
            }
            true
        }
         */
        val appBarConfig : AppBarConfiguration =
            AppBarConfiguration(setOf(R.id.left_frag,R.id.center_frag,R.id.right_frag))
        setupActionBarWithNavController(navController,appBarConfig)
        bnNavView.setupWithNavController(navController)



        /*
        if (savedInstanceState == null) {
            frag1 = Fragment1()
            frag2 = Fragment2()
            myTrans = supportFragmentManager.beginTransaction()
            myTrans!!.add(R.id.dfcontainer, frag1!!, this.TAG_F1)
            myTrans!!.detach(frag1!!)
            myTrans!!.add(R.id.dfcontainer, frag2!!, this.TAG_F2)
            myTrans!!.detach(frag2!!)
            myTrans!!.commit()
        } else {
            frag1 = supportFragmentManager.findFragmentByTag(this.TAG_F1) as Fragment1?
            frag2 = supportFragmentManager.findFragmentByTag(this.TAG_F2) as Fragment2?
        }
        */
    }

    /*
    override fun onSelect(option: Int) {
        val myTrans = supportFragmentManager.beginTransaction()
        when (option) {
            1 -> {
                myTrans.detach(frag2!!)
                myTrans.attach(frag1!!)
            }
            2 -> {
                myTrans.detach(frag1!!)
                myTrans.attach(frag2!!)
            }
        }
        myTrans.commit()
    }*/
}