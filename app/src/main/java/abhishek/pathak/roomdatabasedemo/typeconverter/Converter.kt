package abhishek.pathak.roomdatabasedemo.typeconverter

import androidx.room.TypeConverter
import java.math.BigDecimal

class Converter {
    companion object {

        @TypeConverter
        @JvmStatic
        fun fromBigDecimal(value: BigDecimal): String {
            return value.toString()
        }

        @TypeConverter
        @JvmStatic
        fun toBigDecimal(value: String): BigDecimal {
            return value.toBigDecimal()
        }
    }
}