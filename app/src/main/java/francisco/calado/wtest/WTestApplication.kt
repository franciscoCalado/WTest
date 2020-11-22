package francisco.calado.wtest

import android.app.Application
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class WTestApplication : Application() {

    private lateinit var newsRetrofit: Retrofit

    override fun onCreate() {
        super.onCreate()
        newsRetrofit = Retrofit.Builder()
            .baseUrl("https://5bb1cd166418d70014071c8e.mockapi.io")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create()).build()
    }

    fun getNewsRetrofit() = newsRetrofit
}