package com.example.safeticketsappcompose

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MotionEvent
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.safeticketsappcompose.biometrics.DynamicDataController
import com.example.safeticketsappcompose.biometrics.StaticDataController
import com.example.safeticketsappcompose.databinding.ActivityMainBinding
import com.example.safeticketsappcompose.network.TokenManager

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val dynamicDataController = DynamicDataController()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.login_fragment,R.id.searchFragment2, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        dynamicDataController.initSensorsDataCollector(this)
        StaticDataController.initialize(this)
        TokenManager.initialize(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val x = ev.x
        val y = ev.y

        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                dynamicDataController.collectDynamicData(x, y)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onResume() {
        super.onResume()
        dynamicDataController.startListening()
    }
    override fun onPause() {
        super.onPause()
        dynamicDataController.stopListening()
    }
}