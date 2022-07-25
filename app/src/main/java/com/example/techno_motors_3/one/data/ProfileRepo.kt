package com.example.techno_motors_3.one.data

import com.example.techno_motors_3.one.domain.Profile
import com.example.techno_motors_3.one.domain.ProfileContract
import com.example.techno_motors_3.one.util.Constants

class ProfileRepo : ProfileContract {

    private lateinit var profile: Profile

    /**CRUD */

    override fun createNewProfileEntity() {
        profile = Profile(null,null)  // пока заполняем так - временно
    }

    override fun getProfile(): Profile {
        return profile
    }

    override fun updateProfile(map: Map<String, String>) { //для обновления данных передаем пару ключ - значение

        val currentKey = map.keys.toString()
        val currentValue = map.values.toString()

        when {
            currentKey.contains(Constants.MODEL) -> profile.model = currentValue
            currentKey.contains(Constants.SERVICE_TYPE) -> profile.service_type = currentValue
        }
    }

    /** ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ **/

    /** проверка, есть ли сохраненные данные в Preferences */
    override fun checkSavedStateProfileEntity() {

        if (1 == 0) { // здесь просто заглушка, чтобы получить false
            /** если данные в Preferences есть, то заполняем метод createNewCar здесь */

        } else {
            /**  если данных в  Preferences нет, то делаем некую начальную заполнение данных формы**/
            createNewProfileEntity()
        }
    }

    /** загружаем сохраненные данные из Preferences*/
    override fun loadCarFromPreferences() {
        // todo загрузим данные из Preferences
        // todo создаем новую модель из сохраненных параметров
        // todo делаем createNewCar()
    }
}