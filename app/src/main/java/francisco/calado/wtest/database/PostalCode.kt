package francisco.calado.wtest.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostalCode(
    @PrimaryKey @ColumnInfo(name = "code") val code: String,
    @ColumnInfo(name = "town") val town: String
)