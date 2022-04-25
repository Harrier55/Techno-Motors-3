package com.example.techno_motors_3.one.ui.fragmentAutoMenu

import android.util.Log
import androidx.lifecycle.ViewModel

class AutoViewModel : ViewModel() {

   fun selectedItemMenu(itemMenu: Int){
       Log.d("@@@", "getView: AutoViewModel" + itemMenu)
   }
}