package ru.check_analyziz.database.products


import org.jetbrains.exposed.sql.SqlExpressionBuilder
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import ru.check_analyziz.database.users.UserDTO
import ru.check_analyziz.database.users.UserModel

object ProductModel: Table("checks") {
    val buyer = ProductModel.varchar("buyer", 50)
    val name_product = ProductModel.varchar("name_product", 400)
    val date = ProductModel.varchar("date", 50)
    val price_product = ProductModel.integer("price_product",)
    val shop = ProductModel.varchar("shop", 50)


    fun fetchProduct(): List<ProductDTO> {
        return try {
            transaction {
                ProductModel.selectAll()
                    .map {
                        ProductDTO(

                            it[buyer],
                            it[date],
                            it[name_product],
                            it[ProductModel.price_product],
                            it[shop]
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }



        //fun getAllProduct() = transaction { ProductModel.selectAll().map { ProductDTO(it[buyer], it[date], it[sum], it[shop]) } }
    }
    fun fetchDate(dateTo:String): List<ProductDTO> {
        return try {
            transaction {
                 ProductModel.select { ProductModel.date.greaterEq(dateTo) }
                    .map {
                    ProductDTO(
                        it[buyer],
                        it[date],
                        it[name_product],
                        it[ProductModel.price_product],
                        it[shop]

                    )
                }
            }
        }catch(e:Exception){
            emptyList()
        }
    }

}






