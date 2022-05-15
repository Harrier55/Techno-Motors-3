package com.example.techno_motors_3.one

import android.app.Application
import com.example.techno_motors_3.one.data.CarEntityRepo
import com.example.techno_motors_3.one.data.PromotionImpl
import com.example.techno_motors_3.one.util.ResourceProvider

class App: Application() {

    private val promotionImpl by lazy { PromotionImpl() }
    private val resourceProvider by lazy { ResourceProvider(this) }
    private val carEntityRepo by lazy { CarEntityRepo() }


    override fun onCreate() {
        super.onCreate()
        myAppInstance = this
    }

    companion object{
        lateinit var myAppInstance: App
        private set
    }

    @JvmName("getCarEntityRepo1")
    fun getCarEntityRepo():CarEntityRepo{
        return carEntityRepo
    }

    fun getRepoPromotions():PromotionImpl{
        return promotionImpl
    }

    @JvmName("getResourceProvider1")
    fun getResourceProvider():ResourceProvider{
        return resourceProvider
    }
}