package abhishek.pathak.roomdatabasedemo.image

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "picture")
data class Picture(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val path: String,
    val name: String
)
