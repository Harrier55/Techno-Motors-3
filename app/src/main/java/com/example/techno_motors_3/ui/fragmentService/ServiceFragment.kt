package com.example.techno_motors_3.ui.fragmentService

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.techno_motors_3.R

class ServiceFragment : Fragment() {

    // ВАЖНО - количество картинок должно быть = количеству пунктов меню
    var myIcon = intArrayOf(
        R.drawable.service_item_1,
        R.drawable.service_item_2,
        R.drawable.service_item_3,
        R.drawable.service_item_4,
        R.drawable.service_item_5
    )

    companion object {
        fun newInstance() = ServiceFragment()
    }

    private lateinit var viewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.service_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        // TODO: Use the ViewModel
    }

}