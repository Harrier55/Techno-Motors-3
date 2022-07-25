package com.example.techno_motors_3.one.domain

interface ProfileContract {
    fun createNewProfileEntity()
    fun updateProfile(map: Map<String,String>)
    fun getProfile(): Profile
    fun loadCarFromPreferences()
    fun checkSavedStateProfileEntity()
}