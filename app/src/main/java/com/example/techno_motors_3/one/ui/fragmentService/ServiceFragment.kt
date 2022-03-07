package com.example.techno_motors_3.one.ui.fragmentService

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.ListFragment
import androidx.lifecycle.ViewModelProvider
import com.example.techno_motors_3.R
import com.example.techno_motors_3.databinding.ServiceFragmentBinding

class ServiceFragment : ListFragment() {
    private var _binding:ServiceFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy{ViewModelProvider(this)[ServiceViewModel::class.java]}

    /** ВАЖНО - количество картинок должно быть = количеству пунктов меню*/
    var myIcon = intArrayOf(
        R.drawable.service_item_1,
        R.drawable.service_item_2,
        R.drawable.service_item_3,
        R.drawable.service_item_4,
        R.drawable.service_item_5
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addListToListView()
    }

    private fun addListToListView(){
        val mItem = resources.getStringArray(R.array.item_menu_service_fragment)
        val arrayAdapter = ServiceAdapter(requireContext(),mItem,myIcon)
//        val arrayAdapter = ArrayAdapter<String>(requireContext(),android.R.layout.simple_list_item_1,mItem)
        binding.list.adapter = arrayAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.service_fragment, container, false)
        _binding = ServiceFragmentBinding.bind(view)
        return view
    }

    override fun onDestroy() {
        _binding=null
        super.onDestroy()
    }

    companion object {
        fun newInstanceService() = ServiceFragment()
    }

}