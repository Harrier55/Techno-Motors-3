package com.example.techno_motors_3.one.domain

interface ProfileContract {
//    fun createNewCar(model: String?, service_type: String?) // закрытый метод
    /**для обновления данных передаем ключ - значение */
    fun updateProfile(map: Map<String,String>)
    fun getprofile(): Profile
    fun loadCarFromPreferences()
    fun checkSavedStateCarEntity()
}