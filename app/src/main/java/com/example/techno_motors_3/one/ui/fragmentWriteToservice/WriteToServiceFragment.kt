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
import com.example.techno_motors_3.one.domain.CarEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/** Фрагмент выполняет сбор данных с помощью Диаложков, заполняет форму
 * и отправляет запрос "Запись на ТО " на сервер и возвращает результат в
 * виде сообщения, что запрос успешно доставлен */

/** Данные для формы автомобиля запрашиваются из Preferences ,
 * если данных еще нет, то заполняются по умолчанию в репозитории CarEntityRepo*/

const val BASEURL = "http://192.168.0.111"


class WriteToServiceFragment(private val actionBar: ActionBar) : Fragment() {

    private val TAG = "@@@"
    private val modelList by lazy { resources.getStringArray(R.array.item_menu_model_list) }
    private val serviceList by lazy { resources.getStringArray(R.array.item_TO) }
    private val titleModelList by lazy { R.string.modelList }
    private val titleServiceList by lazy { R.string.serviceList }
    private val carEntityRepo by lazy { App.myAppInstance.getCarEntityRepo() }
    private val apiService by lazy { App.myAppInstance.getApiService() }

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
// Заполнение полей формы данными из репозитория carEntityRepo
        //    fillToDefault()
// заполняем текстовые поля в форме из списков в диаложках
        fillTextViewField()

// нажание кнопки ОТПРАВИТЬ
        binding.buttonSendFormTO.setOnClickListener {
// подготовка формы
            preparationOfForm()
// отправка формы на сервер
            //           webRequest()
        }
    }

    /**Подготовка  формы к отправке,  проверка на незаполненные поля, заполнение полей класса Profile*/
    private fun preparationOfForm() {
        val test = binding.tvSelectModel

        if (binding.tvSelectModel.text.isEmpty()) {
            binding.iconSelectModel.isVisible = true
            showAlarm()
        } else {
            binding.iconSelectModel.isVisible = false
       //  заполняем поле класса для отправки формы
            carEntityRepo.updateCarEntity(CarEntity(model = binding.tvSelectModel.text.toString()))
        }

        if (binding.tvSelectService.text.isEmpty()) {
            binding.iconSelectService.isVisible = true
            showAlarm()
        } else {
            binding.iconSelectService.isVisible = false
            //  заполняем поле класса для отправки формы
            carEntityRepo.updateCarEntity(CarEntity(service_type = binding.tvSelectService.text.toString()))
        }

        if (binding.tvSelectName.text.isEmpty()) {
            binding.iconSelectName.isVisible = true
            showAlarm()
            //  todo вызываем диалог для заполнения данных о клиенте
        } else {
            binding.iconSelectName.isVisible = false
            //  todo заполняем поле класса для отправки формы
        }

        if (binding.tvSelectNumberPhone.text.isEmpty()) {
            binding.iconSelectNumberPhone.isVisible = true
            showAlarm()
            //  todo вызываем диалог для заполнения данных о клиенте
        } else {
            binding.iconSelectNumberPhone.isVisible = false
            //  todo заполняем поле класса для отправки формы
        }
    }

    /** Отправка формы и получение уведомления об отправке */
    private fun webRequest() {
        val call = apiService.sendServiceRequest()
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
    }

    /** Заполнение полей формы данными из Диаложков*/
    private fun fillTextViewField() {
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
        AlertDialog.Builder(requireContext())
            .setTitle("")
            .setMessage(send)
            .show()
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
//                carEntityRepo.updateCarEntity(CarEntity(model = catNames[which])) // обновили значение класса модели
            })
            .show()
    }

    private fun showAlarm() {
        Toast.makeText(
            requireContext(),
            "Заполните, пожалуйста, недостающие поля",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}


