package abhishek.pathak.roomdatabasedemo.local

import abhishek.pathak.roomdatabasedemo.image.Picture
import abhishek.pathak.roomdatabasedemo.image.PictureDao
import abhishek.pathak.roomdatabasedemo.typeconverter.Converter
import abhishek.pathak.roomdatabasedemo.typeconverter.Product
import abhishek.pathak.roomdatabasedemo.typeconverter.ProductDao
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@TypeConverters(Converter::class)
@Database(
    entities = [Note::class, Product::class, Picture::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getNoteDao(): NotesDao
    abstract fun getProductDao(): ProductDao
    abstract fun getPictureDao(): PictureDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "App DB"
                ).addMigrations(MIGRATION_1_2)
                    .build()
            }
            return INSTANCE!!
        }
    }
}