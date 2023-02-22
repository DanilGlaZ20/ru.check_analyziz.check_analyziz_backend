package ru.check_analyziz.cache

import ru.check_analyziz.registration.RegisterReceiveRemote

data class TokenCache(
    val login:String,
    val token:String
)
object InMemoryCache {
    val userList:MutableList<RegisterReceiveRemote> =mutableListOf()
    val token:MutableList<TokenCache> = mutableListOf()

}