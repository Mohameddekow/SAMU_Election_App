package com.example.samuelectionapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.samuelectionapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //set up the toolbar
        val toolBar = binding.mainActivityToolbar
        setSupportActionBar(toolBar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)



        //hiding the navController and the tabLayout in the splash
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.splashFragment -> {
                    supportActionBar?.hide()
                    toolBar.navigationIcon = null

                }
                R.id.homeFragment -> {
                    toolBar.navigationIcon = null
                    supportActionBar?.show()

                }
                R.id.loginFragment -> {
                    toolBar.navigationIcon = null
                    supportActionBar?.show()

                }
                else -> {
                    //toolBar.navigationIcon = null //hiding the back button from home. login , registration screen
                    supportActionBar?.show()
                }
            }
        }

    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.fragmentContainerView)
        return navController.navigateUp()
    }

}