package com.example.kraftopia.ui.main

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.Menu

import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kraftopia.R
import com.example.kraftopia.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var  drawerLayout: DrawerLayout
    private lateinit var  drawerToggle:ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarDrawer.toolbar)

        drawerLayout = binding.drawerLayout
        val toolbar:Toolbar=binding.appBarDrawer.toolbar
        val actionBar:ActionBar=supportActionBar!!
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_drawer)
        drawerToggle= ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name)


        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.isDrawerIndicatorEnabled=true
        drawerToggle.syncState()
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setHomeButtonEnabled(true)
        appBarConfiguration =
            AppBarConfiguration(
                setOf(
                    R.id.nav_shop,
                    R.id.nav_order,
                    R.id.nav_profile,
                    R.id.nav_about,
                    R.id.nav_contact,
                    R.id.nav_terms,
                    R.id.nav_feed,
                    R.id.nav_address
                )
            )
        setupActionBarWithNavController(navController,appBarConfiguration)
        navView.setupWithNavController(navController)
        // val pref:SharedPreferences=applicationContext.getSharedPreferences("user_session",0)
        // Log.d("yolo", "${ pref.getString("session_token",null) }")
        // viewModel.fetchAddress()
        val bottomNavigationView: BottomNavigationView? =findViewById(R.id.bottom_nav_view)
        bottomNavigationView?.setupWithNavController(navController)
        bottomNavigationView?.setOnNavigationItemReselectedListener {  }

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerToggle.syncState()

    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        drawerToggle.onConfigurationChanged(newConfig)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.drawer,menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController=findNavController(R.id.nav_host_fragment_content_drawer)
        return navController.navigateUp(appBarConfiguration)|| super.onSupportNavigateUp()
    }
}