package com.example.techno_motors_3.one.data

import com.example.techno_motors_3.one.domain.PromotionEntity
import com.example.techno_motors_3.one.domain.PromotionEntityContract

class PromotionImpl : PromotionEntityContract {


    private val cashListPromotions: ArrayList<PromotionEntity> = ArrayList()
    init {
        /**  этот метод мы вызываем потому, что в такой реализации
         * список начинает читаться не с 0 позиции
         * нулевая позиция вычитается для Header
         * мы добавляемпустую позицию в начало списка */
        cashListPromotions.add(PromotionEntity())
    }


    override fun createEntity(promotionEntity: PromotionEntity) {
        cashListPromotions.add(promotionEntity)
    }

    override fun getListPromotions(): List<PromotionEntity> {
        return ArrayList<PromotionEntity>(cashListPromotions)
    }

    fun mockRepo() {
        createEntity(
            PromotionEntity(
                "Весенняя диагностика автомобиля",
                "https://cdn.kia.ru/resize/410x275/media-bank/kia-tm/KIA_Inet_AdBanner_410x277_oldfrends.jpg",
                "01 апреля - 30 апреля",
                "Комплексная диагностика всего за 1000 руб*е"
            )
        )
        createEntity(
            PromotionEntity(
                "Проверка и  углов установки колес",
                "https://cdn.kia.ru/resize/410x275/media-bank/kia-tm/410_277.jpg",
                "01 апреля - 30 апреля",
                "Проверка углов установки колес за 0 рублей"
            )
        )
        createEntity(
            PromotionEntity(
                "Антибактериальная обработка системы кондиционирования",
                "https://cdn.kia.ru/resize/410x275/media-bank/kia-tm/410_277antibakterialnaya.jpg",
                "01 апреля - 30 апреля",
                "за 1100 руб"
            )
        )
        createEntity(
            PromotionEntity(
                "Помощь на дороге при прохождении ТО",
                "https://cdn.kia.ru/resize/410x275/media-bank/kia-tm/KIA_Inet_AdBanner_410x277_RoadHelp_HR.jpg",
                "01 апреля - 30 апреля",
                "Помощь в непредвиденной ситуации"
            )
        )
        createEntity(PromotionEntity("No internet", "", "No internet", "description"))

    }


}