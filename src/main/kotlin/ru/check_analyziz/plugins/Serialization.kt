package ru.check_analyziz.plugins

import io.ktor.http.*
import io.ktor.server.plugins.*
import io.ktor.server.application.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.*

fun Application.configureSerialisation()
{
    install(ContentNegotiation)
    {
        json()
    }
}

