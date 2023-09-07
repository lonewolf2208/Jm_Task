package com.example.jumpingmindstask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.jumpingmindstask.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding =  ActivityMainBinding.inflate(layoutInflater)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_home) as NavHostFragment
        val navController: NavController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
        setContentView(binding.root)
    }
}