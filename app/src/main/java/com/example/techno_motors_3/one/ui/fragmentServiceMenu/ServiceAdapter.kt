package com.example.techno_motors_3.one.ui.fragmentServiceMenu

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.techno_motors_3.R

class ServiceAdapter(
    private val context: Context,
    private val callBackFromAdapter: CallBackFromAdapter,
    private val menuList: MutableList<List<Any>>
) : BaseAdapter() {

    override fun getCount() = menuList.size

    override fun getItem(position: Int): Any {
        return menuList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view =
            LayoutInflater.from(context).inflate(R.layout.item_menu_list, parent, false)

        val menuIcon = view.findViewById<ImageView>(R.id.item_service_icon)
        val menuItem = view.findViewById<TextView>(R.id.item_service_text)

        /**через getOrNull получаем элемент списка по индексу*/
        menuIcon.setImageResource(menuList[position].getOrNull(1) as Int)
        menuItem.text = context.resources.getString(menuList[position].getOrNull(2) as Int)

        /**Логика переключения меню фрагментов
         * переключается по ID списка, а это нулевой элемент конкретного List
         * константы для этих ID общие для всего приложения*/
        menuItem.setOnClickListener {
            callBackFromAdapter.onItemClick(menuList[position].getOrNull(0)as Int)
            Log.d("@@@", "getView: " +menuList[position].getOrNull(0)as Int )
        }
        return view
    }
}