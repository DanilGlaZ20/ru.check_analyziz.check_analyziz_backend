package ru.check_analyziz.database.users

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object UserModel: Table("users")
{
    private val login= UserModel.varchar("login",25)
    private val number= UserModel.varchar("number",11)
    private val email= UserModel.varchar("email",100)

    fun insert(userDTO: UserDTO)
    {
        transaction {
            UserModel.insert{
                it[login]=userDTO.login
                it[number]=userDTO.number
                it[email]=userDTO.email ?:" "
            }
        }
    }

    fun fetchUser(login:String): UserDTO? {
        return try {
            transaction {
                val userModel = UserModel.select { UserModel.login.eq(login) }.single()
                UserDTO(
                    login = userModel[UserModel.login],
                    number = userModel[number],
                    email = userModel[email]
                )
            }
        }catch(e:Exception){
            null
        }
    }
}