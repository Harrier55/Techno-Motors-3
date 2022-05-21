package com.example.techno_motors_3.one.util

import android.content.Context
import com.example.techno_motors_3.R



class ResourceProvider(context: Context) {

    /** Заполнение меню Service Fragment*/
    fun getMenuServiceFragment(): MutableList<List<Any>> {
        val listMenuServiceFragment = mutableListOf<List<Any>>()

        listMenuServiceFragment.add(listOf(Constants.GO_WRITE_TO_SERVICE,R.drawable.service_item_1, R.string.title_write_to_service_fragment))
        listMenuServiceFragment.add(listOf(Constants.GO_CALK_SERVICE,R.drawable.service_item_2, R.string.calc_service))
        listMenuServiceFragment.add(listOf(Constants.GO_WRITE_TO_REPAIR,R.drawable.service_item_3, R.string.write_to_repair))
        listMenuServiceFragment.add(listOf(Constants.GO_MATERIALS,R.drawable.service_item_4, R.string.materials))
        listMenuServiceFragment.add(listOf(Constants.GO_CAPACITY,R.drawable.service_item_5, R.string.capacity))

        return listMenuServiceFragment
    }


    val catNames = arrayOf(
        "Васька", "Рыжик", "Мурзик", "Васька", "Рыжик", "Мурзик",
        "Васька", "Рыжик", "Мурзик", "Васька", "Рыжик", "Мурзик", "Васька", "Рыжик", "Мурзик"
    )

}