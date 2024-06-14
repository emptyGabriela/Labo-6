import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.falconteam.laboratorio_5.data.database.entities.PostEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {
    @Query("SELECT * FROM table_post")
    fun observeAll(): Flow<List<PostEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPosts(listPostEntity: List<PostEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(postEntity: PostEntity)

    @Query("UPDATE table_post SET title = :title, description = :description WHERE id = :postId")
    suspend fun updateSelected(title: String, description: String, postId: String)

    @Query("DELETE FROM table_post WHERE id = :postId")
    suspend fun deletePostById(postId: String)

    @Query("DELETE FROM table_post")
    suspend fun deleteAllPosts()
}