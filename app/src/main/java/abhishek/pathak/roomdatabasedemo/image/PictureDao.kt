package abhishek.pathak.roomdatabasedemo.image

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PictureDao {

    @Insert
    fun insert(picture: Picture)

    @Query("SELECT * FROM picture")
    fun getPictures(): List<Picture>
}