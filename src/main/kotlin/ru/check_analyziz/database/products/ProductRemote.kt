package ru.check_analyziz.database.products

import kotlinx.serialization.Serializable
import java.text.DateFormat
import java.text.SimpleDateFormat

@Serializable
data class DataReceiveRemote(val dateFrom:String, val dateBefore:String)

@Serializable
data class SumResponseRemote(val sum:Int?)

@Serializable
data class CheckResponseRemote(var check_analysisList:MutableList<String>)