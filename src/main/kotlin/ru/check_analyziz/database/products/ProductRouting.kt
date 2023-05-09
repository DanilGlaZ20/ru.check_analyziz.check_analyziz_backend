package ru.check_analyziz.database.products

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.check_analyziz.login.LoginController

fun Application.configureProductRouting() {
    routing {
        get("/getAll") {
            val productController = ProductController(call)
            productController.performProduct()
        }

        post("/dateTo") {
            val productController = ProductController(call)
            productController.performData()
        }
        post("/allSum"){
            val productController = ProductController(call)
            productController.performAllSum()
        }
        post("/check_analysis"){
            val productController = ProductController(call)
            productController.performCheckAnalysis()
        }

    }
}