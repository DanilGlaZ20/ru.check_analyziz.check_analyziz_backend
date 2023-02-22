package ru.check_analyziz.registration

import kotlinx.serialization.Serializable

@Serializable
data class RegisterReceiveRemote(val login:String,val email:String, val number:String)

@Serializable
data class RegisterResponseRemote(val token:String)
