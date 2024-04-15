package abhishek.pathak.roomdatabasedemo.typeconverter

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "product")
data class Product(
    @PrimaryKey var name: String,
    var initialPrice: BigDecimal,
    var price: BigDecimal
)

data class PriceChange(
    val name: String,
    val initialPrice: BigDecimal,
    val price: BigDecimal,
    val change: BigDecimal
)
