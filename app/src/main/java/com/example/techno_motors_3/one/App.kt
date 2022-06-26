package com.example.techno_motors_3.one

import android.app.Application
import com.example.techno_motors_3.one.data.CarEntityRepo
import com.example.techno_motors_3.one.data.PromotionImpl
import com.example.techno_motors_3.one.ui.fragmentWriteToservice.BASEURL
import com.example.techno_motors_3.one.util.ResourceProvider
import com.example.techno_motors_3.one.websoket.ServerApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

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
/** Клиент Retrofit сразу с интерфейсом */
    fun getApiService(): ServerApi{
     val retrofit = Retrofit.Builder()
        .baseUrl(BASEURL)
        .addConverterFactory(ScalarsConverterFactory.create())  // без него получить String не получится
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(ServerApi::class.java)
    }

/** Репозиторий CarEntity*/
    @JvmName("getCarEntityRepo1")
    fun getCarEntityRepo():CarEntityRepo{
        return carEntityRepo
    }
/** Репозиторий Акции для Главного экрана**/
    fun getRepoPromotions():PromotionImpl{
        return promotionImpl
    }
/** инстанс Класса ресурсов*/
    @JvmName("getResourceProvider1")
    fun getResourceProvider():ResourceProvider{
        return resourceProvider
    }
}