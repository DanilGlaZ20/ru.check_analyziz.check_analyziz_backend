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
        post("/login"){
            val receive= call.receive<LoginReceiveRemote>()
            val first = InMemoryCache.userList.firstOrNull { it.login == receive.login }
            if (first == null) {
                call.respond(HttpStatusCode.BadRequest, "user not Found")
            } else {
                if (first.number == receive.number) {
                    val token = UUID.randomUUID().toString()
                    InMemoryCache.token.add(TokenCache(login = receive.login, token = token))
                    call.respond(LoginResponseRemote(token = token))
                } else call.respond(HttpStatusCode.BadRequest, "Invalid password")
            }
        }
    }
}


