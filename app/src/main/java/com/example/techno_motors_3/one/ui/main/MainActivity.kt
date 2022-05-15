package com.example.techno_motors_3.one.ui.main

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.techno_motors_3.R
import com.example.techno_motors_3.databinding.ActivityMainBinding
import com.example.techno_motors_3.one.App
import com.example.techno_motors_3.one.ui.fragmentAutoMenu.AutoFragment
import com.example.techno_motors_3.one.ui.fragmentContact.ContactFragment
import com.example.techno_motors_3.one.ui.fragmentHomeMenu.HomeFragment
import com.example.techno_motors_3.one.ui.fragmentServiceMenu.ServiceFragment
import com.example.techno_motors_3.one.ui.fragmentWriteToservice.WriteToServiceFragment

private const val BACK_STACK_MAIN = "BACK_STACK_MAIN"

private const val WRITE_TO_SERVICE = 300
private const val CALK_SERVICE = 301
private const val WRITE_TO_REPAIR = 302
private const val MATERIALS = 303
private const val CAPACITY = 304

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val fragmentManager by lazy { supportFragmentManager }
//    private val actionBar by lazy { this.supportActionBar } // был вариант такой инициализации
    private lateinit var actionBar:
        androidx.appcompat.app.ActionBar
    private val homeFragment by lazy { HomeFragment(actionBar) }
    private val autoFragment by lazy { AutoFragment(actionBar) }
    private val serviceFragment by lazy { ServiceFragment(actionBar,onClickNavigationFragment) }
    private val contactFragment by lazy { ContactFragment(actionBar) }
    private val writeToServiceFragment by lazy { WriteToServiceFragment(actionBar) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar = this.supportActionBar!!
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavigation()
        initRepo()
        actionBar.setTitle(R.string.title_home_fragment)
        launchFragment(homeFragment)
        App.myAppInstance.getRepoPromotions().mockRepo() // create test repo
    }

    private fun initRepo(){
        App.myAppInstance.getCarEntityRepo().checkSavedStateCarEntity()
    }

    private fun launchFragment(fragment: Fragment) {
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun launchFragmentWithPopToBackStack(fragment: Fragment) {
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(BACK_STACK_MAIN)
            .commit()
    }

    private fun navigationFragmentsMain(itemPositionNavi: Int) {
        when (itemPositionNavi) {
            WRITE_TO_SERVICE -> {
                launchFragment(writeToServiceFragment)
            }
        }
    }

    private val onClickNavigationFragment = object : OnClickNavigationFragment {
        override fun onClickMenuItemNavigation(itemPositionNavi: Int) {
            /**   Toast.makeText(this@MainActivity, itemPositionNavi.toString(),Toast.LENGTH_SHORT).show()**/
            navigationFragmentsMain(itemPositionNavi)
        }
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
                    launchFragment(contactFragment)
//                    binding.bottomNavigation.isVisible = false // тест на видимость навигации
                    true
                }
                else -> false
            }
        }
    }

    override fun onBackPressed() {
        if (fragmentManager.backStackEntryCount != 0) {
            fragmentManager.popBackStack(BACK_STACK_MAIN, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        } else {
            showDialogCloseApp()
 //           super.onBackPressed()
        }
    }

    private fun showDialogCloseApp() {
        AlertDialog.Builder(this, R.style.CustomDialog) // учитывается тема Диалога
            .setTitle("Title")
            .setMessage("Хотите выйти из приложения ?")
            .setNegativeButton("Нет", null)
            .setPositiveButton("Да") { dialog, which ->
                finish()
            }
            .show()
    }

//    Toast.makeText(this, "close", Toast.LENGTH_SHORT).show()
}