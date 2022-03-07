package com.example.techno_motors_3.one.data

import com.example.techno_motors_3.one.domain.PromotionEntity
import com.example.techno_motors_3.one.domain.PromotionEntityContract

class PromotionImpl : PromotionEntityContract {


    private val cashListPromotions: ArrayList<PromotionEntity> = ArrayList()


    override fun createEntity(promotionEntity: PromotionEntity) {
        cashListPromotions.add(promotionEntity)
    }

    override fun getListPromotions(): List<PromotionEntity> {
        return ArrayList<PromotionEntity>(cashListPromotions)
    }

    fun mockRepo() {
        createEntity(
            PromotionEntity(
                "Промывка топливной системы, включая материалы и работу мастера",
                "https://autoo.ru/img/uploads/profile/tehno-motors-chelyabinsk-2-y-mikrorayon-akadem-riversayd-1-600.jpg",
                "1500 руб.",
                "Акция действует только по понедельникам"
            )
        )
        createEntity(
            PromotionEntity(
                "Развал-схождение - проверка и регулировка углов установки колес",
                "https://syktyvkar.cena-auto.ru/modules/image/src/images/cache/default_2310_22-940-587-07abfe4f.jpg",
                "1680 руб.",
                "Акция действует только по средам"
            )
        )
        createEntity(
            PromotionEntity(
                "Счастливые часы в Техно-Моторс,\n" +
                        "скидка на все работы",
                "https://s3.eu-central-1.amazonaws.com/images.hipdir/302357/uigg0zfuwsrruhhg3prb7trpbad5zlue.jpg",
                "Скидка15 %",
                "Акция действует только по воскресениям"
            )
        )
        createEntity(
            PromotionEntity(
                "Скидка на регламентное техническое обслуживание автомобилей KIA",
                "https://autoo.ru/img/uploads/profile/tehno-motors-chelyabinsk-2-y-mikrorayon-akadem-riversayd-1-600.jpg",
                "Скидка 10 %",
                "Акция действует в текущем месяце"
            )
        )
        createEntity(PromotionEntity("No internet", "", "SALE 15 %", "description"))
    }


}