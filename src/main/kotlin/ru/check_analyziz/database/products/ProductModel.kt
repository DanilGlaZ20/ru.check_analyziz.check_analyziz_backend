package ru.check_analyziz.database.products


import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object ProductModel: Table("product") {
    val buyer = ProductModel.varchar("buyer", 50)
    val date = ProductModel.varchar("date", 20)
    val sum = ProductModel.varchar("sum", 12)
    val shop = ProductModel.varchar("shop", 50)


    fun fetchProduct(): List<ProductDTO> {
        return try {
            transaction {
                ProductModel.selectAll().toList()
                    .map { ProductDTO(
                        it[buyer],
                        it[date],
                        it[sum],
                        it[shop])
                    }
            }

        } catch (e: Exception) {
            emptyList()
        }

    }

    //fun getAllProduct() = transaction { ProductModel.selectAll().map { ProductDTO(it[buyer], it[date], it[sum], it[shop]) } }
}