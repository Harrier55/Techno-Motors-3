package com.example.techno_motors_3.one.ui.fragmentServiceMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.ListFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.techno_motors_3.R
import com.example.techno_motors_3.databinding.ServiceFragmentBinding
import com.example.techno_motors_3.one.ui.main.OnClickNavigationFragment


/**  задача этого фрагмента - просто меню, которое переключает на другие фрагменты*/

private const val WRITE_TO_SERVICE = 300
private const val CALK_SERVICE = 301
private const val WRITE_TO_REPAIR = 302
private const val MATERIALS = 303
private const val CAPACITY = 304

class ServiceFragment(private val actionBar: ActionBar,
                      private val onClickNavigationFragment: OnClickNavigationFragment) :
    ListFragment() {
    private var _binding: ServiceFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy { ViewModelProvider(this)[ServiceViewModel::class.java] }
    private val adapter by lazy {
        ServiceAdapter(
            requireContext(),
            onItemClickListenerServiceFragment
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar.setTitle(R.string.title_service_fragment)
    }

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

    private val onItemClickListenerServiceFragment =
        object : OnItemClickListenerServiceFragmentAdapter {
            override fun onItemClick(position: Int) {
                onClickNavigationFragment.onClickMenuItemNavigation(position)
            }
        }




}