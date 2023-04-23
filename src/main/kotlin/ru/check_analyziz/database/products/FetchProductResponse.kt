package ru.check_analyziz.database.products

import kotlinx.serialization.Serializable

@Serializable

data class FetchProductResponse(
    val products:List<ProductResponse>
)

@Serializable
data class ProductResponse(
        val buyer:String,
        val date:String,
        val sum:String,
        val shop:String
        )