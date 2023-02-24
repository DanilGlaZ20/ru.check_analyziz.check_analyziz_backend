package ru.check_analyziz.registration

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

import ru.check_analyziz.utils.isValidEmail

fun Application.configureRegistRouting(){
    routing{
        post("/register"){
            val registerController=RegisterController(call)
            registerController.registerNewUser()
            val receive=call.receive<RegisterReceiveRemote>()
            if(!receive.email.isValidEmail()) call.respond(HttpStatusCode.BadRequest, "Email is not valid")
        }

    }
}