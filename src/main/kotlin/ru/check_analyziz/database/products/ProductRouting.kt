package ru.check_analyziz.database.products

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureProductRouting() {
    routing {
        get("/getAll") {
            val productController = ProductController(call)
            productController.performProduct()

        }
    }
}