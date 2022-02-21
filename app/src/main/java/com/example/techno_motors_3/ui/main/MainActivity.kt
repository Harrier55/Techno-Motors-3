package com.example.techno_motors_3.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.techno_motors_3.R
import com.example.techno_motors_3.databinding.ActivityMainBinding
import com.example.techno_motors_3.ui.fragmentAuto.AutoFragment
import com.example.techno_motors_3.ui.fragmentHome.HomeFragment
import com.example.techno_motors_3.ui.fragmentService.ServiceFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val fragmentManager by lazy { supportFragmentManager }
    private val homeFragment by lazy { HomeFragment() }
    private val autoFragment by lazy { AutoFragment() }
    private val serviceFragment by lazy { ServiceFragment() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        launchFragment(homeFragment)
        initBottomNavigation()


    }

    private fun launchFragment(fragment: Fragment) {
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }


    private fun initBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    launchFragment(homeFragment)
                    true
                }
                R.id.menu_auto -> {
                    launchFragment(autoFragment)
                    true
                }
                R.id.menu_service -> {
                    launchFragment(serviceFragment)
                    true
                }
                R.id.menu_contacts -> {
                    true
                }
                else -> false
            }

        }


    }




}