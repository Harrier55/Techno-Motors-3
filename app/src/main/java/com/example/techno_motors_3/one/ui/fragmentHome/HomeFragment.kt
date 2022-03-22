package com.example.techno_motors_3.one.ui.fragmentHome

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.techno_motors_3.R
import com.example.techno_motors_3.databinding.HomeFragmentBinding
import com.example.techno_motors_3.one.App
import com.example.techno_motors_3.one.domain.PromotionEntity

class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { ViewModelProvider(this)[HomeViewModel::class.java] }
    private val myAdapter by lazy { AdapterHomeFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myAdapter.refreshList(
            App.myAppInstance.getRepoPromotions()
                .getListPromotions() as ArrayList<PromotionEntity>

        )

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerviewHomeFragment.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerviewHomeFragment.adapter = myAdapter
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_fragment, container, false)
        _binding = HomeFragmentBinding.bind(view)
        return view
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}