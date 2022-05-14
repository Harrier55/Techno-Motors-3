package com.example.techno_motors_3.one.domain

interface CarEntityContract {
    fun createNewCar(carEntity: CarEntity)
    fun loadCarFromPreferences():CarEntity
    fun getCar():CarEntity
}