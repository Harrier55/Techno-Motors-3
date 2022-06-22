package com.example.techno_motors_3.one.ui.fragmentWriteToservice


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import com.example.techno_motors_3.R
import com.example.techno_motors_3.databinding.WriteToServiceBinding
import com.example.techno_motors_3.one.App

/** Фрагмент выполняет сбор данных с помощью Диаложков, заполняет форму
 * и отправляет запрос "Запись на ТО " на сервер и возвращает результат в
 * виде сообщения, что запрос успешно доставлен */

/** Данные для формы автомобиля запрашиваются из Preferences ,
 * если данных еще нет, то заполняются по умолчанию в репозитории CarEntityRepo*/


class WriteToServiceFragment(private val actionBar: ActionBar) : Fragment() {

    private val TAG = "@@@"
    private val modelList by lazy { resources.getStringArray(R.array.item_menu_model_list) }
    private val serviceList by lazy { resources.getStringArray(R.array.item_TO) }
    private val titleModelList by lazy { R.string.modelList }
    private val titleServiceList by lazy { R.string.serviceList }

    private var _binding: WriteToServiceBinding? = null
    private val binding
        get() = _binding!!

    private val carEntityRepo by lazy { App.myAppInstance.getCarEntityRepo() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar.setTitle(R.string.title_write_to_service_fragment) //
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
            dialogForSelection(binding.tvSelectModel, titleModelList, modelList)
        }

        binding.tvSelectService.setOnClickListener {
            dialogForSelection(binding.tvSelectService, titleServiceList, serviceList)
        }
    }

    private fun dialogForSelection(
        selectTextView: TextView,
        titleDialog: Int,
        menuList: Array<String>
    ) {
        AlertDialog.Builder(requireContext())
            .setTitle(titleDialog)
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





