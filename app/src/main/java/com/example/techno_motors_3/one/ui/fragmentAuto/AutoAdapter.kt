package com.example.techno_motors_3.one.ui.fragmentAuto

import android.annotation.SuppressLint
import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.techno_motors_3.R

class AutoAdapter(
    private val context: Context,
    private val myItem: Array<String>,
    private val myIcon: IntArray,
   private val onItemClickListenerAutoFragment: OnItemClickListenerAutoFragment
) : BaseAdapter() {

    override fun getCount() = myItem.size

    override fun getItem(position: Int): Any {
        return myItem[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {


        val convertView =
            LayoutInflater.from(context).inflate(R.layout.item_auto_list, parent, false)

        val menuIcon = convertView.findViewById<ImageView>(R.id.item_service_icon)
        val menuItem = convertView.findViewById<TextView>(R.id.item_service_text)

        menuIcon.setImageResource(myIcon[position])
        menuItem.text = myItem[position]

        menuItem.setOnClickListener {
            onItemClickListenerAutoFragment.onItemClick(position)
        }
        return convertView
    }
}