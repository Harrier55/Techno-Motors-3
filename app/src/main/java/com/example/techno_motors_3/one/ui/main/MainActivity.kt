package com.example.techno_motors_3.one.ui.main

import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.techno_motors_3.R
import com.example.techno_motors_3.databinding.ActivityMainBinding
import com.example.techno_motors_3.one.MyApp
import com.example.techno_motors_3.one.ui.fragmentAuto.AutoFragment
import com.example.techno_motors_3.one.ui.fragmentContact.ContactFragment
import com.example.techno_motors_3.one.ui.fragmentHome.HomeFragment
import com.example.techno_motors_3.one.ui.fragmentService.ServiceFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    private val fragmentManager by lazy { supportFragmentManager }
    private val homeFragment by lazy { HomeFragment() }
    private val autoFragment by lazy { AutoFragment() }
    private val serviceFragment by lazy { ServiceFragment() }
    private val contactFragment by lazy { ContactFragment() }

    private val actionBar by lazy { this.supportActionBar}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavigation()
        actionBar?.setTitle(R.string.menu_home)


        launchFragment(homeFragment)


        MyApp.myAppInstance.getRepoPromotions().mockRepo() // create test repo
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
                    actionBar?.setTitle(R.string.menu_home)
                    true
                }
                R.id.menu_auto -> {
                    launchFragment(autoFragment)
                    actionBar?.setTitle(R.string.menu_auto)
                    true
                }
                R.id.menu_service -> {
                    launchFragment(serviceFragment)
                    actionBar?.setTitle(R.string.menu_service)
                    true
                }
                R.id.menu_contacts -> {
                    actionBar?.setTitle(R.string.menu_contacts)
                    launchFragment(contactFragment)
                    true
                }
                else -> false
            }

        }


    }




}