package com.example.techno_motors_3.ui.fragmentAuto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.ListFragment
import androidx.lifecycle.ViewModelProvider
import com.example.techno_motors_3.R
import com.example.techno_motors_3.databinding.AutoFragmentBinding

class AutoFragment : ListFragment() {

    private lateinit var binding:AutoFragmentBinding

    companion object {
        fun newInstance() = AutoFragment()
    }

    private lateinit var viewModel: AutoViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view = inflater.inflate(R.layout.auto_fragment, container, false)
        binding = AutoFragmentBinding.bind(view)
        val mItem = resources.getStringArray(R.array.item_menu_car_fragment)

        val arrayAdapter = ArrayAdapter<String>(requireContext(),android.R.layout.simple_list_item_1,mItem)
        binding.list.adapter = arrayAdapter

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AutoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}