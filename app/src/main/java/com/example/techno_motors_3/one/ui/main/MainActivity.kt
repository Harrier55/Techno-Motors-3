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
import com.example.techno_motors_3.one.util.Constants

private const val BACK_STACK_MAIN = "BACK_STACK_MAIN"


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val fragmentManager by lazy { supportFragmentManager }

    /**был вариант такой инициализации actionBar, но он требовал проверки на null*/
    //    private val actionBar by lazy { this.supportActionBar }
    private lateinit var actionBar: androidx.appcompat.app.ActionBar
    private val homeFragment by lazy { HomeFragment(actionBar) }
    private val autoFragment by lazy { AutoFragment(actionBar) }
    private val serviceFragment by lazy { ServiceFragment(actionBar, onClickNavigationFragment) }
    private val contactFragment by lazy { ContactFragment(actionBar) }
    private val writeToServiceFragment by lazy { WriteToServiceFragment(actionBar) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar = this.supportActionBar!!
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavigation()
        initRepo()
        launchFragment(homeFragment)
    }

    private fun initRepo() {
        /**первоначальная инициализация для проверки. Если есть данные в Preferences
         * то грузим их, иначе создаем новый объект CarEntity*/
        App.myAppInstance.getCarEntityRepo().checkSavedStateCarEntity()

        /**mock для репозитория Новости (первая страница)*/
        App.myAppInstance.getRepoPromotions().mockRepo() // create test mock repo
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
            Constants.GO_WRITE_TO_SERVICE -> {
                launchFragmentWithPopToBackStack(writeToServiceFragment)
            }
            Constants.GO_WRITE_TO_REPAIR ->{
                //todo
            }
        }
    }

    /**логика переключения фрагментов :
     *  Используем  для каждого фрагмента
     * своего идентификатора - константы
     * которые  перечислены в классе Constants
     * и навигация реализована в navigationFragmentsMain*/

    /** callback для навигации переключения фрагментов*/
    private val onClickNavigationFragment = object : OnClickNavigationFragment {
        override fun onClickMenuItemNavigation(itemPositionNavi: Int) {
//            Toast.makeText(this@MainActivity, itemPositionNavi.toString(), Toast.LENGTH_SHORT).show()
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