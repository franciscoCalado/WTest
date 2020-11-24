package francisco.calado.wtest

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class WTestFullApplication : WTestApplication() {

    private lateinit var newsRetrofitV2: Retrofit

    override fun onCreate() {
        super.onCreate()
        newsRetrofitV2 = Retrofit.Builder()
            .baseUrl("https://5bb1d1e66418d70014071c9c.mockapi.io")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create()).build()
    }

    override fun getNewsRetrofit() = newsRetrofitV2

}