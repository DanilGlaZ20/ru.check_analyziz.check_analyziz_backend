package ru.check_analyziz.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

import ru.check_analyziz.database.tokens.TokenDTO
import ru.check_analyziz.database.tokens.TokenModel
import ru.check_analyziz.database.users.UserModel
import ru.check_analyziz.registration.RegisterResponseRemote
import java.util.*

class LoginController(private val call:ApplicationCall) {
    suspend fun performLogin() {
        val loginReceiveRemote = call.receive<LoginReceiveRemote>()
        val userDTO = UserModel.fetchUser(loginReceiveRemote.login)
        if (userDTO == null) {
            call.respond(HttpStatusCode.Conflict, "User not found")
        } else {
            if (userDTO.number == loginReceiveRemote.number) {
                val token = UUID.randomUUID().toString()
                TokenModel.insert(
                    TokenDTO(
                        id = UUID.randomUUID().toString(),
                        login = loginReceiveRemote.login,
                        token = token
                    )
                )

                call.respond(RegisterResponseRemote(token = token))

            } else call.respond(HttpStatusCode.BadRequest, "Invalid password")


        }


    }
}

