package ru.check_analyziz.database.products

import kotlinx.serialization.Serializable


@Serializable
data class AnalisisCheck(val shop:String,val price:Int?)
