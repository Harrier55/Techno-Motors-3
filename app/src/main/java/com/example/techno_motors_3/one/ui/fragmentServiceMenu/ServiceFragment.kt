package com.example.techno_motors_3.one.ui.fragmentServiceMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.ListFragment
import com.example.techno_motors_3.R
import com.example.techno_motors_3.databinding.ServiceFragmentBinding
import com.example.techno_motors_3.one.App
import com.example.techno_motors_3.one.ui.main.OnClickNavigationFragment

/**  задача этого фрагмента - просто меню, которое переключает на другие фрагменты
 * основная навигация в main activity*/
class ServiceFragment(private val actionBar: ActionBar,
                      private val onClickNavigationFragment: OnClickNavigationFragment) :
    ListFragment() {
    private var _binding: ServiceFragmentBinding? = null
    private val binding get() = _binding!!
//    private val viewModel by lazy { ViewModelProvider(this)[ServiceViewModel::class.java] }
    private val menuList by lazy {
        App.myAppInstance.getResourceProvider().getMenuServiceFragment()
    }
    private val adapter by lazy {
        ServiceAdapter(
            requireContext(),
            callBackFromAdapter,
            menuList
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar.setTitle(R.string.title_service_fragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actionBar.setTitle(R.string.title_service_fragment)
        binding.list.adapter = adapter
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

    private val callBackFromAdapter =
        object : CallBackFromAdapter {
            override fun onItemClick(position: Int) {
                onClickNavigationFragment.onClickMenuItemNavigation(position)
            }
        }

}