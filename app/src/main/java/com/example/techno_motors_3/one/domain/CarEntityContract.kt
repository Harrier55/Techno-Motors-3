package com.example.techno_motors_3.one.domain

interface CarEntityContract {
//    fun createNewCar(model: String?, service_type: String?) // закрытый метод
    /**для обновления данных передаем ключ - значение */
    fun updateCarEntity(map: Map<String,String>)
    fun getCar(): CarEntity
    fun loadCarFromPreferences()
    fun checkSavedStateCarEntity()
}