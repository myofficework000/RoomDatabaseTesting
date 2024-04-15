package abhishek.pathak.roomdatabasedemo.relations

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity("User")
data class User(
    @PrimaryKey val userId: Long,
    val userName: String,
    val email: String
)

@Entity(
    tableName = "post",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["userId"],
        childColumns = ["userId"]
    )]
)
data class Post(
    @PrimaryKey val postId: Long,
    val userId: Long,
    val content: String
)

@Entity(
    tableName = "comment",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["userId"],
        childColumns = ["userId"]
    ), ForeignKey(
        entity = Post::class,
        parentColumns = ["postId"],
        childColumns = ["postId"]
    )]
)
data class Comment(
    @PrimaryKey val commentId: String,
    val userId: Long,
    val postId: Long,
    val text: String
)
