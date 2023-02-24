package ru.check_analyziz.registration

import io.ktor.http.*
import io.ktor.server.application.*

import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.check_analyziz.database.tokens.TokenDTO
import ru.check_analyziz.database.tokens.TokenModel

import ru.check_analyziz.database.users.UserDTO
import ru.check_analyziz.database.users.UserModel
import ru.check_analyziz.utils.isValidEmail


import java.util.UUID

class RegisterController(private val call: ApplicationCall)
{
    suspend fun registerNewUser()//запрос на регистрацию
    {
        val registerReceiveRemote=call.receive<RegisterReceiveRemote>()
        if(!registerReceiveRemote.email.isValidEmail()) call.respond(HttpStatusCode.BadRequest, "Email is not valid")

        val userDTO= UserModel.fetchUser(registerReceiveRemote.login)
        if(userDTO!=null){ //есть ли такой пользователь
            call.respond(HttpStatusCode.Conflict, "User already exists")
        } else {
            val token = UUID.randomUUID().toString()
            UserModel.insert(UserDTO(login = registerReceiveRemote.login, number = registerReceiveRemote.number, email = registerReceiveRemote.email))

            TokenModel.insert(TokenDTO(id = UUID.randomUUID().toString(), login = registerReceiveRemote.login, token = token))

            call.respond(RegisterResponseRemote(token=token))
        }

    }

}