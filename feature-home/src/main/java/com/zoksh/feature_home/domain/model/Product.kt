package com.zoksh.feature_home.domain.model

data class Product(
    val id: String,
    val name: String,
    val imageUrl: String,
    val price: Double,
    val isFavorite: Boolean
)
