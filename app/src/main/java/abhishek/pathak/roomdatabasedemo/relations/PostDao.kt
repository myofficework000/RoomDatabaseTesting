package abhishek.pathak.roomdatabasedemo.relations

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface PostDao {

    @Transaction
    @Query("Select * from post")
    fun getAllCommentsOnAPost(): List<PostWithComments>


}