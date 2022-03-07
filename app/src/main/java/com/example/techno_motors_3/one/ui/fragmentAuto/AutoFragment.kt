package com.example.techno_motors_3.one.ui.fragmentAuto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.ListFragment
import androidx.lifecycle.ViewModelProvider
import com.example.techno_motors_3.R
import com.example.techno_motors_3.databinding.AutoFragmentBinding
import com.example.techno_motors_3.one.ui.fragmentService.ServiceFragment

class AutoFragment : ListFragment() {

    private  var _binding:AutoFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy { ViewModelProvider(this)[AutoViewModel::class.java] }

    private var myIcon = intArrayOf(
        R.drawable.car_item_1,
        R.drawable.car_item_2,
        R.drawable.car_item_3,
        R.drawable.car_item_4,
        R.drawable.car_item_5
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view = inflater.inflate(R.layout.auto_fragment, container, false)
        _binding = AutoFragmentBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addListToListView()

    }

    private fun addListToListView(){
        val mItem = resources.getStringArray(R.array.item_menu_car_fragment)
        val arrayAdapter = AutoAdapter(requireContext(),mItem,myIcon,onItemClickListenerAutoFragment)
//        val arrayAdapter = ArrayAdapter<String>(requireContext(),android.R.layout.simple_list_item_1,mItem)
        binding.list.adapter = arrayAdapter
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private var onItemClickListenerAutoFragment = object: OnItemClickListenerAutoFragment{
        override fun onItemClick(item: Int) {
            viewModel.selectedItemMenu(item)

        }

    }

}