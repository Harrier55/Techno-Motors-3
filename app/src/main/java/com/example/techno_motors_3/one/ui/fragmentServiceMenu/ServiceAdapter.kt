package com.example.techno_motors_3.one.ui.fragmentServiceMenu

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.techno_motors_3.R

class ServiceAdapter(
    private val context: Context,
    private val onItemClickListenerServiceFragment: OnItemClickListenerServiceFragmentAdapter

) : BaseAdapter() {

    private var menuList: MutableList<List<Any>> = mutableListOf()

    fun refreshListMenu(menuList: MutableList<List<Any>>){
        this.menuList = menuList
        notifyDataSetChanged()
    }
    override fun getCount() = menuList.size

    override fun getItem(position: Int): Any {
        return menuList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {


        val convertView =
            LayoutInflater.from(context).inflate(R.layout.item_menu_list, parent, false)

        val menuIcon = convertView.findViewById<ImageView>(R.id.item_service_icon)
        val menuItem = convertView.findViewById<TextView>(R.id.item_service_text)

        menuIcon.setImageResource(menuList[position].getOrNull(0) as Int)
        menuItem.text = context.resources.getString(menuList[position].getOrNull(1) as Int)

        menuItem.setOnClickListener {
               onItemClickListenerServiceFragment.onItemClick(position+300)
        }
        return convertView
    }
}