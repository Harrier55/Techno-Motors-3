package com.example.techno_motors_3.one.util

import android.content.Context
import com.example.techno_motors_3.R

class ResourceProvider(context: Context) {

    /** Заполнение меню Service Fragment*/
    fun getMenuServiceFragment(): MutableList<List<Any>> {
         val listMenuServiceFragment = mutableListOf<List<Any>>()

        listMenuServiceFragment.add(listOf(R.drawable.service_item_1, R.string.title_write_to_service_fragment))
        listMenuServiceFragment.add(listOf(R.drawable.service_item_2, R.string.calc_service))
        listMenuServiceFragment.add(listOf(R.drawable.service_item_3, R.string.write_to_repair))
        listMenuServiceFragment.add(listOf(R.drawable.service_item_4, R.string.write_to_repair))
        listMenuServiceFragment.add(listOf(R.drawable.service_item_5, R.string.write_to_repair))
        return listMenuServiceFragment
    }


    val catNames = arrayOf(
        "Васька", "Рыжик", "Мурзик", "Васька", "Рыжик", "Мурзик",
        "Васька", "Рыжик", "Мурзик", "Васька", "Рыжик", "Мурзик", "Васька", "Рыжик", "Мурзик"
    )

}