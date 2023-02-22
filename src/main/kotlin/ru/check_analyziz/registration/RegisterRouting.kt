package ru.check_analyziz.registration

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.check_analyziz.cache.InMemoryCache
import ru.check_analyziz.cache.TokenCache

import ru.check_analyziz.utils.isValidEmail

import java.util.*


fun Application.configureRegistRouting(){
    routing{
        post("/register"){
            val receive= call.receive<RegisterReceiveRemote>()
            if(!receive.email.isValidEmail()) call.respond(HttpStatusCode.BadRequest, "Email is not valid")
            val token=UUID.randomUUID().toString()
            InMemoryCache.userList.add(receive)
            InMemoryCache.token.add(TokenCache(login=receive.login, token=token ))
            call.respond(RegisterResponseRemote(token=token))
        }
    }
}