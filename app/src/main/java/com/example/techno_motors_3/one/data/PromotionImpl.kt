package com.example.techno_motors_3.one.data

import com.example.techno_motors_3.one.domain.PromotionEntity
import com.example.techno_motors_3.one.domain.PromotionEntityContract

class PromotionImpl: PromotionEntityContract {



    private val cashListPromotions: ArrayList<PromotionEntity> = ArrayList()


    override fun createEntity(promotionEntity: PromotionEntity) {
       cashListPromotions.add(promotionEntity)
    }

    override fun getListPromotions(): List<PromotionEntity> {
        return ArrayList<PromotionEntity>(cashListPromotions)
    }

    fun mockRepo(){
        createEntity(PromotionEntity("Title mock internet","https://autoo.ru/img/uploads/profile/tehno-motors-chelyabinsk-2-y-mikrorayon-akadem-riversayd-1-600.jpg","diccount","описание"))
        createEntity(PromotionEntity("Title mock internet","https://syktyvkar.cena-auto.ru/modules/image/src/images/cache/default_2310_22-940-587-07abfe4f.jpg","diccount","описание"))
        createEntity(PromotionEntity("Title mock internet","https://s3.eu-central-1.amazonaws.com/images.hipdir/302357/uigg0zfuwsrruhhg3prb7trpbad5zlue.jpg","diccount","описание"))
        createEntity(PromotionEntity("Title mock internet","https://autoo.ru/img/uploads/profile/tehno-motors-chelyabinsk-2-y-mikrorayon-akadem-riversayd-1-600.jpg","diccount","описание"))
        createEntity(PromotionEntity("No internet","","SALE 15 %","description"))
    }



}