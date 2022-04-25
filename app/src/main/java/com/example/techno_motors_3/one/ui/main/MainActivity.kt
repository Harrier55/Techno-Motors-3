package com.example.techno_motors_3.one.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.techno_motors_3.R
import com.example.techno_motors_3.databinding.ActivityMainBinding
import com.example.techno_motors_3.one.App
import com.example.techno_motors_3.one.ui.fragmentAutoMenu.AutoFragment
import com.example.techno_motors_3.one.ui.fragmentContact.ContactFragment
import com.example.techno_motors_3.one.ui.fragmentHomeMenu.HomeFragment
import com.example.techno_motors_3.one.ui.fragmentServiceMenu.ServiceFragment
import com.example.techno_motors_3.one.ui.fragmentWriteToservice.WriteToServiceFragment

private const val WRITE_TO_SERVICE = 300
private const val CALK_SERVICE = 301
private const val WRITE_TO_REPAIR = 302
private const val MATERIALS = 303
private const val CAPACITY = 304

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    private val fragmentManager by lazy { supportFragmentManager }
    private val actionBar by lazy { this.supportActionBar}

    private val homeFragment by lazy { HomeFragment() }
    private val autoFragment by lazy { AutoFragment() }
    private val serviceFragment by lazy { ServiceFragment(onClickNavigationFragment) }
    private val contactFragment by lazy { ContactFragment() }
    private val writeToServiceFragment by lazy { WriteToServiceFragment() }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavigation()
        actionBar?.setTitle(R.string.menu_home)
        launchFragment(homeFragment,R.string.menu_home)
        App.myAppInstance.getRepoPromotions().mockRepo() // create test repo
    }

    private fun launchFragment(fragment: Fragment, actionBarTitle:Int) {
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
        actionBar?.setTitle(actionBarTitle)
    }

    private fun navigationFragments(itemPositionNavi: Int){
        when(itemPositionNavi){
            WRITE_TO_SERVICE ->{
                launchFragment(writeToServiceFragment,R.string.write_to_service)
            }
        }

    }

    private val onClickNavigationFragment = object : OnClickNavigationFragment{
        override fun onClickMenuItemNavigation(itemPositionNavi: Int) {
//            Toast.makeText(this@MainActivity, itemPositionNavi.toString(),Toast.LENGTH_SHORT).show()
            navigationFragments(itemPositionNavi)
        }
    }

    private fun initBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    launchFragment(homeFragment,R.string.menu_home)
                    true
                }
                R.id.menu_auto -> {
                    launchFragment(autoFragment,R.string.menu_auto)
                    true
                }
                R.id.menu_service -> {
                    launchFragment(serviceFragment,R.string.menu_service)
                    true
                }
                R.id.menu_contacts -> {
                    launchFragment(contactFragment,R.string.menu_contacts)
//                    binding.bottomNavigation.isVisible = false // тест на видимость навигации
                    true
                }
                else -> false
            }
        }
    }


}