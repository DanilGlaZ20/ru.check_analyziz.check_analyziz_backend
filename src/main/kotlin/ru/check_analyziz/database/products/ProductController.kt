package ru.check_analyziz.database.products

import io.ktor.server.application.*
import io.ktor.server.response.*

class ProductController (private val call: ApplicationCall) {
    suspend fun performProduct() {

        call.respond(ProductModel.fetchProduct())
    }
}
