package ru.check_analyziz.database.products

import kotlinx.serialization.Serializable


@Serializable
data class ProductDTO(
    val buyer: String,
    val date: String,
    val name_product: String,
    val price_product: Int,
    val shop: String
)


