package com.example.techno_motors_3.ui.fragmentAuto

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.techno_motors_3.R

class AutoFragment : Fragment() {

    companion object {
        fun newInstance() = AutoFragment()
    }

    private lateinit var viewModel: AutoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.auto_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AutoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}