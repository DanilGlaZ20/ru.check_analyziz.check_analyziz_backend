package ru.check_analyziz.database.products


import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.greaterEq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.lessEq
import org.jetbrains.exposed.sql.transactions.transaction
import java.awt.AWTEventMulticaster.add
import java.util.Objects

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


    }

    fun fetchDate(dateFrom: String, dateBefore: String): List<ProductDTO> {
        return try {
            transaction {
                ProductModel.select { ProductModel.date.greaterEq(dateFrom) and ProductModel.date.lessEq(dateBefore) }
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
    }

    fun checkAnalysis(dateFrom: String, dateBefore: String):MutableList<AnalisisCheck> {
        var check_analysis= mutableListOf<AnalisisCheck>()

            transaction {
               ProductModel.slice(shop, price_product.sum())
                    .select(date.greaterEq(dateFrom) and date.lessEq(dateBefore))
                    .groupBy(shop)
                    .forEach{
                        //check_analysis.add()
                         //check_analysis.add("${it[shop]}:${it[price_product.sum()]}")
                        check_analysis.add(AnalisisCheck(shop=it[shop],price=it[price_product.sum()]))
                    }

            }
        return check_analysis

    }


    fun allSum(dateFrom: String, dateBefore: String): Int? {
        var sum: Int? = 0
        transaction {
            for (product in ProductModel.slice(ProductModel.price_product.sum())
                .select(ProductModel.date.greaterEq(dateFrom) and ProductModel.date.lessEq(dateBefore))) {
                //println( "${product[ProductModel.shop]}: ${product[ProductModel.price_product.sum()]}")
                sum = product[ProductModel.price_product.sum()]
            }

        }
        return sum

    }

}

























