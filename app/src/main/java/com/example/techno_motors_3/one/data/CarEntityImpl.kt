package com.example.techno_motors_3.one.data

import com.example.techno_motors_3.one.domain.CarEntity
import com.example.techno_motors_3.one.domain.CarEntityContract

class CarEntityImpl: CarEntityContract {

    private lateinit var carEntity:CarEntity

    override fun createNewCar(carEntity: CarEntity) {

    }

    override fun loadCarFromPreferences():CarEntity {
        return carEntity
    }

    override fun getCar(): CarEntity {
    /**загружаем данные из Preferences */
        val res = loadCarFromPreferences()
        if(1 == null){ // здесь просто заглушка, чтобы получить false
            /** данные в Preferences есть */
            carEntity = res
        } else{
            createNewCar(CarEntity("newModel", "service_test"))
        }
        return carEntity
    }
}