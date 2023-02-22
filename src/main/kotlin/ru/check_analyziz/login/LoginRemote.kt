package ru.check_analyziz.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginReceiveRemote(val login:String, val number:String)

@Serializable
data class LoginResponseRemote(val token:String)