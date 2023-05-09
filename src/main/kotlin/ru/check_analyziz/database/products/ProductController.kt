package ru.check_analyziz.database.products

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.check_analyziz.database.users.UserModel

class ProductController (private val call: ApplicationCall) {
    suspend fun performProduct() {

        call.respond(ProductModel.fetchProduct())
    }
    suspend fun performData() {
        val dataReceiveRemote = call.receive<DataReceiveRemote>()
        call.respond(ProductModel.fetchDate(dataReceiveRemote.dateFrom,dataReceiveRemote.dateBefore))
    }

    suspend fun performAllSum(){
        val dataReceiveRemote = call.receive<DataReceiveRemote>()
        val sum=ProductModel.allSum(dataReceiveRemote.dateFrom,dataReceiveRemote.dateBefore)
        call.respond(SumResponseRemote(sum=sum))
    }

    suspend fun performCheckAnalysis(){
        val dataReceiveRemote = call.receive<DataReceiveRemote>()
        val check_analysis=ProductModel.checkAnalysis(dataReceiveRemote.dateFrom,dataReceiveRemote.dateBefore)
        //call.respond(CheckResponseRemote(check_analysisList =check_analysis ))
        call.respond(ProductModel.checkAnalysis(dataReceiveRemote.dateFrom,dataReceiveRemote.dateBefore))
    }

}


