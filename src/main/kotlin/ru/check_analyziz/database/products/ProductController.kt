package ru.check_analyziz.database.products

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class ProductController (private val call: ApplicationCall) {
    suspend fun performProduct() {

        call.respond(ProductModel.fetchProduct())
    }
    suspend fun performData() {
        val dataReceiveRemote = call.receive<DataReceiveRemote>()
        val productModel=ProductModel.fetchDate(dataReceiveRemote.dateTo)
        call.respond(ProductModel.fetchDate(dataReceiveRemote.dateTo))
    }
}
