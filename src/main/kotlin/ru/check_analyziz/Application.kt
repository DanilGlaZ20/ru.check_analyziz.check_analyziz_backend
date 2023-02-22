package ru.check_analyziz

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import ru.check_analyziz.login.configureLoginRouting
import ru.check_analyziz.plugins.*
import ru.check_analyziz.registration.configureRegistRouting

fun main() {
    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureRouting()//типо датаслой
    configureLoginRouting()
    configureRegistRouting()
    configureSerialisation()

}
