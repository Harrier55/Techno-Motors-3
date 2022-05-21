package com.example.techno_motors_3.one.ui.fragmentServiceMenu

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.techno_motors_3.one.App

private const val WRITE_TO_SERVICE = 0
private const val CALK_SERVICE = 1
private const val WRITE_TO_REPAIR = 2
private const val MATERIALS = 3
private const val CAPACITY = 4

/** Пока нтгде не используется*/

class ServiceViewModel(application: Application) : AndroidViewModel(application) {

//    private val menuList by lazy {
//        App.myAppInstance.getResourceProvider().getMenuServiceFragment()
//    }
    private var menu = MutableLiveData<MutableList<List<Any>>>()

    init {
//        menu.postValue(menuList)
    }

    fun getMenu():MutableLiveData<MutableList<List<Any>>>{
        return menu
    }

    fun runSelectedItemMenu(position:Int){

        when(position){
            WRITE_TO_SERVICE ->{

            }
            CALK_SERVICE ->{}
            WRITE_TO_REPAIR->{}

        }
    }
    private var menuServiceFragment = MutableLiveData<Array<String>>()
    fun getMenuServiceFragment():MutableLiveData<Array<String>> {
        return  menuServiceFragment
    }
}