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
import com.example.techno_motors_3.one.domain.ServiceRequestForm
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

    //    private val TAG = "@@@"
    private val modelList by lazy { resources.getStringArray(R.array.item_menu_model_list) }
    private val serviceList by lazy { resources.getStringArray(R.array.item_TO) }
    private val titleModelList by lazy { R.string.modelList }
    private val titleServiceList by lazy { R.string.serviceList }
    private val profileRepo by lazy { App.myAppInstance.getProfile() }
    private val apiService by lazy { App.myAppInstance.getApiService() }
    private val serviceRequestForm by lazy { ServiceRequestForm() }
    private var counterForCheckNonNullField: Int = 0
    private var isSendingFormToServer: Boolean = true // флаг-  форма не отправлена

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
            if (isSendingFormToServer) {
                /** если  форма еще не отпралялась, то проверяем и отправляем ее*/
                sendFormDataToServer()
            } else {
                Toast.makeText(requireContext(),"куда то выходим", Toast.LENGTH_SHORT).show()
                isSendingFormToServer = true
            }
        }
    }

   /** проверка на заполнение данных и отправка формы*/
    private fun sendFormDataToServer() {
        // проверка  формы, чтобы значения были заполнены
        checkingForNonEmptyValues()

        // заполнить класс для отправки данных на сервер
        fillClassForSending()

        // todo заполнить профиль обновленными данными
        fillClassProfile()

        // отправка формы на сервер
        if (counterForCheckNonNullField == 4) {
            binding.buttonSendFormTO.isClickable = false
            webRequest()
        } else {
            showAlarmEmptyValues()
        }
    }

    /** формируем класс для отправки данных на сервер */
    private fun fillClassForSending() {
        serviceRequestForm.model = binding.tvSelectModel.text.toString()
        serviceRequestForm.service_type = binding.tvSelectService.text.toString()
        serviceRequestForm.name = binding.tvSelectName.text?.toString()
        serviceRequestForm.phone = binding.tvSelectNumberPhone.text?.toString()
    }

    /** записать данные формы в Профиль */
    private fun fillClassProfile() {
        profileRepo.updateProfile(mapOf(Constants.MODEL to binding.tvSelectModel.text.toString()))
        profileRepo.updateProfile(mapOf(Constants.SERVICE_TYPE to binding.tvSelectService.text.toString()))
    }

    /**Подготовка  формы к отправке,  проверка на незаполненные поля
     * счетчик считает количество заполненных полей*/
    private fun checkingForNonEmptyValues() {
        counterForCheckNonNullField = 0

        if (binding.tvSelectModel.text?.length == 0) {
            binding.iconSelectModel.isVisible = true
        } else {
            binding.iconSelectModel.isVisible = false
            counterForCheckNonNullField++
        }

        if (binding.tvSelectService.text?.length == 0) {
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

    /** Web запрос и получение ответа */
    private fun webRequest() {

            val call = apiService.sendServiceRequest(serviceRequestForm)
            call.clone().enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful && response.code() == 200) {
                        notificationOfSending(response.body().toString())
                    }
                }
                override fun onFailure(call: Call<String>, t: Throwable) {
                    notificationOfSending(getString(R.string.error_send_form_request))
                    // todo желательно обработать ошибку
                }
            })
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

    /** Заполнение полей формы данными из репозитория Профиля*/
    private fun fillToDefault() {
        binding.tvSelectModel.text = profileRepo.getProfile().model

    }

    /**Диаложка уведомления успеха отправки формы*/
    private fun notificationOfSending(send: String) {
        isSendingFormToServer = false // ДА - форма отправлен успешно
        binding.buttonSendFormTO.isClickable = true
        binding.buttonSendFormTO.text = "Закрыть форму и выйти"
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

    /** Диаложка, что не все поля заполнены */
    private fun showAlarmEmptyValues() {
        MyDialogs.notificationOfSending(requireContext(), R.string.please_fill_in_the_fields)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}


