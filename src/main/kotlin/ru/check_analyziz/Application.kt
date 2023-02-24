package ru.check_analyziz

import io.ktor.server.engine.*
import io.ktor.server.cio.*
import org.jetbrains.exposed.sql.Database
import ru.check_analyziz.login.configureLoginRouting
import ru.check_analyziz.plugins.*
import ru.check_analyziz.registration.configureRegistRouting

fun main() {
    Database.connect(url = "jdbc:postgresql://localhost:5432/postgres", driver="org.postgresql.Driver", user = "postgres", password = "110875an")
    embeddedServer(CIO, port = 8080, host = "0.0.0.0") {
        configureRouting()//типо датаслой
        configureLoginRouting()
        configureRegistRouting()
        configureSerialisation()

    }.start(wait = true)
}

