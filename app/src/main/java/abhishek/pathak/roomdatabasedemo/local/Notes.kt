package abhishek.pathak.roomdatabasedemo.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Note")
data class Note(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "desc") val desc: String? = "Nothing",
    @ColumnInfo(name = "body") val body: String? = "Nothing",
    @ColumnInfo(name = "color") val color: String? = "Black",
    @ColumnInfo(name = "fav") val fav: String? = "false",
    @ColumnInfo(name = "lock") val lock: String? = "false",
) {
    constructor(title: String, desc: String) : this(0, title, desc)
    constructor(title: String, desc: String, body: String) : this(0, title, desc, body)
    constructor(title: String, desc: String, body: String, color: String) : this(
        0,
        title,
        desc,
        body,
        color
    )
}
