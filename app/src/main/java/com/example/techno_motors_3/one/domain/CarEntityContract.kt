package com.example.techno_motors_3.one.domain

interface CarEntityContract {
//    fun createNewCar(model: String?, service_type: String?) // закрытый метод
    fun updateCarEntity(carEntityUpdate: CarEntity)
    fun getCar(): CarEntity
    fun loadCarFromPreferences()
    fun checkSavedStateCarEntity()
}