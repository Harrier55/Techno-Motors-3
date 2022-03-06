package com.example.techno_motors_3.one.domain

interface PromotionEntityContract {
    fun createEntity(promotionEntity: PromotionEntity)
    fun getListPromotions():List<PromotionEntity>
}