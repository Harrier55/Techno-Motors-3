package com.example.techno_motors_3.one.data

import com.example.techno_motors_3.one.domain.CarEntity
import com.example.techno_motors_3.one.domain.CarEntityContract

class CarEntityRepo: CarEntityContract {

    private lateinit var carEntity:CarEntity

    override fun createNewCar(model: String, service_type: String) {
        carEntity = CarEntity(model,service_type)
    }

    override fun updateCarEntity(carEntity: CarEntity) {
        this.carEntity = carEntity
    }

    override fun loadCarFromPreferences() {
        // загрузим данные из Preferences
       // создаем новую модель из сохраненных параметров
       // делаем createNewCar()
    }

    override fun getCar(): CarEntity {
         return carEntity
    }

    /** проверка, есть ли сохраненные данные в Preferences */
    override fun checkSavedStateCarEntity() {

        if(1==0){ // здесь просто заглушка, чтобы получить false
            /** если данные в Preferences есть */

        } else{
             createNewCar("newModel", "service_test")
        }
    }
}