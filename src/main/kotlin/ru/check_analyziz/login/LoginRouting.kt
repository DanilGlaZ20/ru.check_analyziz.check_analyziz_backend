package ru.check_analyziz.login;

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.check_analyziz.cache.InMemoryCache
import ru.check_analyziz.cache.TokenCache


import java.util.*


fun Application.configureLoginRouting() {
    routing {
        post("/login") {
            val loginController = LoginController(call)
            loginController.performLogin()
        }
    }
}


