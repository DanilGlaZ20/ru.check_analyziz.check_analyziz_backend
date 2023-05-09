package ru.check_analyziz

import io.ktor.server.engine.*
import io.ktor.server.cio.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Sum
import ru.check_analyziz.database.products.ProductModel
//import ru.check_analyziz.database.products.ProductModel.checkAnalysis
import ru.check_analyziz.database.products.configureProductRouting
import ru.check_analyziz.login.configureLoginRouting
import ru.check_analyziz.plugins.*
import ru.check_analyziz.registration.configureRegistRouting

fun main() {
    Database.connect(url = "jdbc:postgresql://localhost:5432/postgres", driver="org.postgresql.Driver", user = "postgres", password = "110875an")
    //println(ProductModel.checkAnalysis("2023-01-01", "2023-04-05"))
    //println( ProductModel.checkAnalysis("2023-01-01", "2023-04-05"))
    embeddedServer(CIO, port = 8080, host = "0.0.0.0") {
        configureRouting()//типо датаслой
        configureLoginRouting()
        configureRegistRouting()
        configureSerialisation()
        configureProductRouting()
    }.start(wait = true)
}

