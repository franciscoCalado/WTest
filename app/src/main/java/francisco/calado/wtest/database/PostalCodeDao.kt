package francisco.calado.wtest.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

@Dao
interface PostalCodeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPostalCode(postalCode: PostalCode)

    @Query("SELECT COUNT(*) FROM postalcode")
    fun getEntrySize(): Single<Int>

    @Query("SELECT * FROM postalcode where code LIKE :code AND town LIKE :town")
    fun searchPostalCode(town: String, code: String): Single<List<PostalCode>>

}