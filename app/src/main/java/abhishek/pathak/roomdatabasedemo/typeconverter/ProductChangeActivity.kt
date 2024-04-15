package abhishek.pathak.roomdatabasedemo.typeconverter

import abhishek.pathak.roomdatabasedemo.databinding.ActivityProductChangeBinding
import abhishek.pathak.roomdatabasedemo.local.AppDatabase
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ProductChangeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductChangeBinding
    private lateinit var appDatabase: AppDatabase
    private lateinit var productDao: ProductDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductChangeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDB()
        insertDummyData()
        initViews()
    }

    private fun insertDummyData() {
        val list = mutableListOf<Product>().apply {
            add(Product("BitCoin", "0.0324354654".toBigDecimal(), "0.032435254".toBigDecimal()))
            add(Product("LiteCoin", "0.0524354654".toBigDecimal(), "0.022435254".toBigDecimal()))
            add(Product("DogeCoin", "0.0724354654".toBigDecimal(), "0.012435254".toBigDecimal()))
        }
        productDao.insertProducts(list)
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        val priceChange = productDao.priceChange()
        val first = priceChange[0]
        val second = priceChange[1]
        val third = priceChange[2]

        with(binding) {
            firstRowTxtName.text = first.name
            firstRowInitialPrice.text = first.initialPrice.toString()
            firstRowTxtPrice.text = first.price.toString()

            secondRowTxtName.text = second.name
            secondRowInitialPrice.text = second.initialPrice.toString()
            secondRowTxtPrice.text = second.price.toString()

            thirdRowTxtName.text = third.name
            thirdRowInitialPrice.text = third.initialPrice.toString()
            thirdRowTxtPrice.text = third.price.toString()
        }
    }

    private fun initDB() {
        appDatabase = AppDatabase.getInstance(this.applicationContext)!!
        productDao = appDatabase.getProductDao()
    }
}