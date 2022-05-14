package com.example.techno_motors_3.one.ui.fragmentWriteToservice


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.techno_motors_3.R
import com.example.techno_motors_3.databinding.WriteToServiceBinding
import androidx.appcompat.app.ActionBar
import com.example.techno_motors_3.one.App


class WriteToServiceFragment(private val actionBar: ActionBar) : Fragment() {

    private  var _binding: WriteToServiceBinding? = null
    private val binding
    get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar.setTitle(R.string.title_write_to_service_fragment)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.write_to_service, container, false)
        _binding = WriteToServiceBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.tvSelectModel.setOnClickListener {
            Toast.makeText(requireContext(),"text", Toast.LENGTH_SHORT).show()
            dialog()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun dialog(){
         val catNames = arrayOf("Васька", "Рыжик", "Мурзик","Васька", "Рыжик", "Мурзик",
            "Васька", "Рыжик", "Мурзик","Васька", "Рыжик", "Мурзик","Васька", "Рыжик", "Мурзик")


        AlertDialog.Builder(requireContext())
            .setTitle("Выбрать модель автомобиля")
            .setItems(catNames, DialogInterface.OnClickListener { dialog, which ->
                Toast.makeText(activity, "Выбранный кот: ${catNames[which]}",
                    Toast.LENGTH_SHORT).show()
            })
            .show()


    }




}





