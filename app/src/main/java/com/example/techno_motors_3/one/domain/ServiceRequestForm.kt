package com.example.techno_motors_3.one.domain

data class ServiceRequestForm(
    var id: Int              = 431,
    var model:String?        = null,
    var service_type:String? = null,
    var name:String?         = null,
    var phone:String?        = null
)
