package com.example.techno_motors_3.one.ui.fragmentWriteToservice


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.techno_motors_3.R
import com.example.techno_motors_3.databinding.WriteToServiceBinding
import com.example.techno_motors_3.one.App
import com.example.techno_motors_3.one.util.Constants
import com.example.techno_motors_3.one.util.MyDialogs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/** Фрагмент выполняет сбор данных с помощью Диаложков, заполняет форму
 * и отправляет запрос "Запись на ТО " на сервер и возвращает результат в
 * виде сообщения, что запрос успешно доставлен */

/** Данные для формы автомобиля запрашиваются из Preferences ,
 * если данных еще нет, то они заполняются из Диаложков*/

//const val BASEURL = "http://192.168.0.111"


class WriteToServiceFragment(private val actionBar: ActionBar) : Fragment() {

    private val TAG = "@@@"
    private val modelList by lazy { resources.getStringArray(R.array.item_menu_model_list) }
    private val serviceList by lazy { resources.getStringArray(R.array.item_TO) }
    private val titleModelList by lazy { R.string.modelList }
    private val titleServiceList by lazy { R.string.serviceList }
    private val carEntityRepo by lazy { App.myAppInstance.getCarEntityRepo() }
    private val apiService by lazy { App.myAppInstance.getApiService() }
    private var counterForCheckNonNullField: Int = 0

    private var _binding: WriteToServiceBinding? = null
    private val binding
        get() = _binding!!


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
        super.onViewCreated(view, savedInstanceState)
        // todo Заполнение полей формы данными из репозитория Profile
        // fillToDefault()
        /** заполняем текстовые поля в форме из диаложков */
        fillFieldFromDialog()

        /** нажание кнопки ОТПРАВИТЬ */
        binding.buttonSendFormTO.setOnClickListener {
            // проверка  формы, чтобы значения были заполнены
            checkingForNonEmptyValues()
            // todo заполнить класс для отправки данных на сервер
            // todo заполнить профиль обновленными данными
            fillClassProfile()
            // отправка формы на сервер
            webRequest()
        }
    }

    private fun fillClassForSending() {
            // todo заполнить класс для отправки данных на сервер
    }

    private fun fillClassProfile() {
        carEntityRepo.updateCarEntity(mapOf(Constants.MODEL to binding.tvSelectModel.text.toString()))
        carEntityRepo.updateCarEntity(mapOf(Constants.SERVICE_TYPE to binding.tvSelectService.text.toString()))
    }


    /**Подготовка  формы к отправке,  проверка на незаполненные поля*/
    private fun checkingForNonEmptyValues() {
        counterForCheckNonNullField = 0

        if (binding.tvSelectModel.text.isEmpty()) {
            binding.iconSelectModel.isVisible = true
        } else {
            binding.iconSelectModel.isVisible = false
            counterForCheckNonNullField++
        }

        if (binding.tvSelectService.text.isEmpty()) {
            binding.iconSelectService.isVisible = true
        } else {
            binding.iconSelectService.isVisible = false
            counterForCheckNonNullField++
        }

        if (binding.tvSelectName.text?.length == 0) {
            binding.iconSelectName.isVisible = true
        } else {
            binding.iconSelectName.isVisible = false
            counterForCheckNonNullField++
        }

        if (binding.tvSelectNumberPhone.text?.length == 0) {
            binding.iconSelectNumberPhone.isVisible = true
        } else {
            binding.iconSelectNumberPhone.isVisible = false
            counterForCheckNonNullField++
        }
    }

    /** Отправка формы и получение уведомления об отправке */
    /** проверка, чтобы все значения были заполнены - счетчик считает количество заполненных полей
     * если все поля заполнены, то выплняется запрос */
    private fun webRequest() {
        if (counterForCheckNonNullField == 4) {

            val call = apiService.sendServiceRequest(carEntityRepo)
            call.clone().enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful && response.code() == 200) {
                        notificationOfSending(response.body().toString())
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    notificationOfSending(getString(R.string.error_send_form_request))
                }
            })
        } else {
            showAlarmEmptyValues()
        }
    }

    /** Заполнение полей формы данными из Диаложков*/
    private fun fillFieldFromDialog() {
        binding.tvSelectModel.setOnClickListener {
            dialogForSelection(binding.tvSelectModel, titleModelList, modelList)
        }

        binding.tvSelectService.setOnClickListener {
            dialogForSelection(binding.tvSelectService, titleServiceList, serviceList)
        }
    }

    /** Заполнение полей формы данными из репозитория carEntityRepo*/
    private fun fillToDefault() {
        binding.tvSelectModel.text = carEntityRepo.getCar().model
        binding.tvSelectService.text = carEntityRepo.getCar().service_type
    }

    /**Диаложка уведомления успеха/ошибки отправки формы*/
    private fun notificationOfSending(send: String) {
        MyDialogs.notificationOfSending(requireContext(), send)
    }

    /** Диаложка для заполнения формы выбора модели, ТО и пр.*/
    private fun dialogForSelection(
        selectTextView: TextView,
        titleDialog: Int,
        menuList: Array<String>
    ) {
        AlertDialog.Builder(requireContext())
            .setTitle(titleDialog)
            .setItems(menuList, DialogInterface.OnClickListener { dialog, which ->
                selectTextView.text = menuList[which]
            })
            .show()
    }

    /** сообщение, что не все поля заполнены */
    private fun showAlarmEmptyValues() {
        MyDialogs.notificationOfSending(requireContext(), R.string.please_fill_in_the_fields)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}


