package com.example.techno_motors_3.one.ui.fragmentWriteToservice


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.techno_motors_3.R
import com.example.techno_motors_3.databinding.WriteToServiceBinding
import androidx.appcompat.app.ActionBar
import com.example.techno_motors_3.one.App


class WriteToServiceFragment(private val actionBar: ActionBar) : Fragment() {

    private val TAG = "@@@"
    private val modelList by lazy { resources.getStringArray(R.array.item_menu_model_list) }
    private val serviceList by lazy { resources.getStringArray(R.array.item_TO) }

    private var _binding: WriteToServiceBinding? = null
    private val binding
        get() = _binding!!

    private val carEntityRepo by lazy { App.myAppInstance.getCarEntityRepo() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar.setTitle(R.string.title_write_to_service_fragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView: ")
        val view = inflater.inflate(R.layout.write_to_service, container, false)
        // init binding
        _binding = WriteToServiceBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // init textView field
        binding.tvSelectModel.text = carEntityRepo.getCar().model
        binding.tvSelectService.text = carEntityRepo.getCar().service_type

        fillTextViewField()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun fillTextViewField() {
        binding.tvSelectModel.setOnClickListener {
            dialogForSelection(binding.tvSelectModel, modelList)
        }

        binding.tvSelectService.setOnClickListener {
            dialogForSelection(binding.tvSelectService, serviceList)
        }
    }

    private fun dialogForSelection(selectTextView: TextView, menuList: Array<String>) {
        AlertDialog.Builder(requireContext())
            .setTitle("Выбрать модель автомобиля")
            .setItems(menuList, DialogInterface.OnClickListener { dialog, which ->
                selectTextView.text = menuList[which]
//                carEntityRepo.updateCarEntity(CarEntity(model = catNames[which])) // обновили значение класса модели
            })
            .show()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}





