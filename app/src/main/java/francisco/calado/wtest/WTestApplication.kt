package francisco.calado.wtest

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import francisco.calado.wtest.database.AppDatabase
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

open class WTestApplication : Application() {

    private lateinit var newsRetrofit: Retrofit
    private lateinit var database: RoomDatabase

    override fun onCreate() {
        super.onCreate()
        newsRetrofit = Retrofit.Builder()
            .baseUrl("https://5bb1cd166418d70014071c8e.mockapi.io")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create()).build()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "app_database"
        ).build()


    }

    open fun getNewsRetrofit() = newsRetrofit
    fun getDatabase() = database
}