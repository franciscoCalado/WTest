package francisco.calado.wtest.home.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import francisco.calado.wtest.R
import francisco.calado.wtest.WTestApplication
import francisco.calado.wtest.home.HomeNavigator
import francisco.calado.wtest.home.NewsRepository
import francisco.calado.wtest.home.service.NewsService

class HomeActivity : AppCompatActivity() {

    private val homeNavigator: HomeNavigator by lazy {
        HomeNavigator(supportFragmentManager, R.id.fragment_container)
    }
    private lateinit var newsRepository: NewsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        newsRepository =
            NewsRepository(NewsService((application as WTestApplication).getNewsRetrofit()))
        homeNavigator.navigateToInitialFragment(HomeFragment.newInstance())
    }

    fun getNavigator() = homeNavigator

    fun getNewsRepository() = newsRepository
}