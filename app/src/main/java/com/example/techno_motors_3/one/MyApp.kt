package com.example.techno_motors_3.one

import android.app.Application
import com.example.techno_motors_3.one.data.PromotionImpl

class MyApp: Application() {

    private val promotionImpl by lazy { PromotionImpl() }

    override fun onCreate() {
        super.onCreate()
        myAppInstance = this
    }

    companion object{
        lateinit var myAppInstance: MyApp
        private set
    }

    fun getRepoPromotions():PromotionImpl{
        return promotionImpl
    }
}