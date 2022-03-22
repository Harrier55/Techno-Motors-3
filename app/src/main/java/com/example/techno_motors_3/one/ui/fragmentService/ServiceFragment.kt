package com.example.techno_motors_3.one.ui.fragmentService

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.ListFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.techno_motors_3.R
import com.example.techno_motors_3.databinding.ServiceFragmentBinding
import com.example.techno_motors_3.one.App


/**  задача этого фрагмента - просто меню, которое переключает на другие фрагменты*/

private const val WRITE_TO_SERVICE = 0
private const val CALK_SERVICE = 1
private const val WRITE_TO_REPAIR = 2
private const val MATERIALS = 3
private const val CAPACITY = 4

class ServiceFragment : ListFragment() {
    private var _binding: ServiceFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy { ViewModelProvider(this)[ServiceViewModel::class.java] }
    private val adapter by lazy { ServiceAdapter(
        requireContext(),
        onItemClickListenerServiceFragment
    ) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.list.adapter = adapter
        viewModel.getMenu().observe(viewLifecycleOwner, Observer {
            adapter.refreshListMenu(it)
        })
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
        _binding = null
        super.onDestroy()
    }

    private val onItemClickListenerServiceFragment = object : OnItemClickListenerServiceFragment {
        override fun onItemClick(position: Int) {
            Toast.makeText(requireContext(), position.toString(), Toast.LENGTH_SHORT).show()
            runSelectedItemMenu(position)
        }
    }

    fun runSelectedItemMenu(position: Int) {
        val fragmentManager = activity?.supportFragmentManager
        when (position) {
            WRITE_TO_SERVICE -> {}
            CALK_SERVICE -> {}
            WRITE_TO_REPAIR -> {}
            MATERIALS -> {}
            CAPACITY -> {}
        }
    }


}