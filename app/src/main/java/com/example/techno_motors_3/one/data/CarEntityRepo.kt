package com.example.techno_motors_3.one.data

import com.example.techno_motors_3.one.domain.CarEntity
import com.example.techno_motors_3.one.domain.CarEntityContract
import com.example.techno_motors_3.one.util.Constants

class CarEntityRepo : CarEntityContract {

    private lateinit var carEntity: CarEntity

    private fun createNewCar(model: String?, service_type: String?) {
        carEntity = CarEntity(model, service_type)
    }

    override fun updateCarEntity(map: Map<String, String>) {
        val currentKey = map.keys.toString()
        val currentValue = map.values.toString()
        when {
            currentKey.contains(Constants.MODEL) -> carEntity.model = currentValue
            currentKey.contains(Constants.SERVICE_TYPE) -> carEntity.service_type = currentValue
        }
    }
    /** загружаем сохраненные данные из Preferences*/
    override fun loadCarFromPreferences() {
        // todo загрузим данные из Preferences
        // todo создаем новую модель из сохраненных параметров
        // todo делаем createNewCar()
    }

    override fun getCar(): CarEntity {
        return carEntity
    }

    /** проверка, есть ли сохраненные данные в Preferences */
    override fun checkSavedStateCarEntity() {

        if (1 == 0) { // здесь просто заглушка, чтобы получить false
            /** если данные в Preferences есть, то заполняем метод createNewCar здесь */

        } else {
            /**  если данных в  Preferences нет, то делаем некую начальную заполнение данных формы**/
            createNewCar(null, null)
        }
    }
}