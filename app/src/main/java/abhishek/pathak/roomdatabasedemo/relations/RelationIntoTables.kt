package abhishek.pathak.roomdatabasedemo.relations

import androidx.room.Embedded
import androidx.room.Relation

data class PostWithComments(
    @Embedded val post: Post,
    @Relation(
        parentColumn = "postId",
        entityColumn = "postId"
    )
    val comments: List<Comment>
)