package francisco.calado.wtest.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PostalCode::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun postalCodeDao(): PostalCodeDao
}