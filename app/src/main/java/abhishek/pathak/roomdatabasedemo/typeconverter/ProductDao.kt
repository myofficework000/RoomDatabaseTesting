package abhishek.pathak.roomdatabasedemo.typeconverter

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {

    @Query("Select * from product")
    fun allProducts(): List<Product>

    @Query("Select *, price - initialPrice as change FROM product")
    fun priceChange(): List<PriceChange>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(product: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProducts(products: List<Product>)
}